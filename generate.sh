#!/usr/bin/env bash

usage="Usage: $(basename "$0") (deploy | diff | serve)

Commands:
  deploy	Upload project to GitHub
  diff		Compare locally generated application to live application
  serve		Generate and serve application (auto-reloads on changes)"

# Push to LordKrabo (repo for JS) first - change this as time goes on.
# Needed to also create a fork for LordKrabo/org.geppetto.model
TARGET_REPO="LordKrabo/org.geppetto.core"
GH_BRANCH="test_automatic_push"

#OUTPUT_DIR="src/main/resources/projects"
OUTPUT_DIR="output"
REMOTE_DIR="remote"

GEP_DIR=https://raw.githubusercontent.com/LordKrabo/org.geppetto.core
CURRENT_DIR="$(pwd)"

PY_CMD="python3"
SERVER="http.server"
PORT="8000"

rootPath="$(cd "$(dirname "${BASH_SOURCE[0]})")" && pwd)"
#rootPath="$(pwd)"

echo_changes() {
	for xmi in $(find $CURRENT_DIR -type f -name "GeppettoModel.xml"); do
		branches=()
		
		branches=$(git branch | awk -F ' +' '! /\(no branch\)/ {print $2}')
		branch_array=($branches)		
		
		for index in "${!branch_array[@]}";
		do
			echo $index
			echo "Branch: ${branch_array[$index]}"
			sed -i.bak -e "s/${branch_array[$index]}/$GH_BRANCH/g" $xmi
		done
	done 
}

generate_application() {
  #Based on http://zonca.github.io/2013/09/automatically-build-pelican-and-publish-to-github-pages.html
  if [ "$TRAVIS" == "true" ]; then
    # Ensure that builds triggered by pull requests are not deployed
    if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
      echo "Successfully built pull request #TRAVIS_PULL_REQUEST."
      exit 0
    fi
  
    echo "Deploying application to $GH_BRANCH" branch of $TARGET_REPO."
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "Travis CI"
  else
   echo "Pull request for #TRAVIS_PULL_REQUEST unsuccessful"
   cd "$rootPath" || exit 1
  fi

  # Pull hash and commit message of the most recent commit
  commitHash=$(git rev-parse HEAD)
  commitMessage=$(git log -1 --pretty=%B)

  #Clone the GitHub branch and rsync it with the newly generated files
  GITHUB_REPO=https://${GH_TOKEN:-git}@github.com/${TARGET_REPO}.git
  git clone --branch $GH_BRANCH --depth 1 "$GITHUB_REPO" $REMOTE_DIR &> /dev/null
  echo "$rootPath"
	
	for xmi in $(find $CURRENT_DIR -type f -name "GeppettoModel.xmi"); do
		branches=()
		branches=$(git branch | awk -F ' +' '! /\(no branch\)/ {print $2}')
		branch_array=($branches)		
		for index in "${!branch_array[@]}";
		do
			#echo $index
			#echo "Branch: ${branch_array[$index]}"
			#sed -i.bak -e "s/${branch_array[$index]}/$GH_BRANCH/g" $xmi
			sed -i"" -e "s/${branch_array[$index]}/$GH_BRANCH/g" $xmi
		done
	done 
  #for ECORE in $(ls "${CURRENT_DIR}"src/main/resources/projects/*.xmi); do
  #printf $(echo $(basename $ECORE))
  #JOB=$(echo $(basename $ECORE) | sed 's/.xmi$//g')
  #sed -i 's/git name-rev --name-only $GH_BRANCH/g' $ECORE
  #done

  rsync -r --exclude=.git --delete $OUTPUT_DIR/ $REMOTE_DIR/
  #rsync -r --exclude=.git --delete $OUTPUT_DIR/ ./
  #rsync -r --exclude=.git --delete ../$OUTPUT_DIR/ .
  #rsync -r --exclude=.git
  pushd $REMOTE_DIR > /dev/null

  git add -A
  git status -s
  
  $1 # execute the function passed as an argument
}
  
# Push changes using TRAVIS

push_changes() {
  if [ "$TRAVIS" == "true" ]; then
    longMessage="Generated by $commitHash; pushed by build #$TRAVIS_BUILD_NUMBER."
    git commit -m "$commitMessage" -m "$longMessage"
    git push origin $GH_BRANCH &> /dev/null || echo "Push failed."
 else
    read -rp "Push changes to GitHub? [y/N] " response
    if [[ $response =~ ^[Yy]$ ]]; then
    	git commit -m "$commitMessage" -m "Generated by $commitHash."
    	git push origin $GH_BRANCH
    fi

    popd > /dev/null
    rm -rf --$REMOTE_DIR $OUTPUT_DIR && echo "Removed $REMOTE_DIR and $OUTPUT_DIR."
 fi
}

case "$1" in
  'echo')
    echo "GeppettoModel.xmi files"
    echo_changes
    ;;
    
  'deploy')
    echo "Deploy"
    generate_application push_changes
    ;;

  'diff')
    generate_site 'git --no-pager diff --cached --color-words'
    ;;
   
  'serve')
    developPath=${root}/develop
    local_ip-$(ifconfig | grep 'inet' | awk 'NR==2 {print $2}')
    
    #Seed directory with site content
    cd "$rootPath" > /dev/null
    echo "Serving HTTP at $(tput bold)${local_ip}:${PORT}$(tput sgr0)."

    cleanup() {
      pkill -f $SERVER
      cd "$rootPath" && rm -r "$developPath" && echo && exit 0
    }

    trap cleanup SIGINT

    (cd "$developPath" || exit 1; $PY_CMD -m $SERVER $PORT 1> /dev/null) &
    wait
    ;;

   *)
    echo "$usage"
    exit 2
    ;;

esac



