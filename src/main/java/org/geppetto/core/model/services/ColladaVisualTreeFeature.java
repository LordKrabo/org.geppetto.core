package org.geppetto.core.model.services;

import java.util.Collection;
import java.util.Iterator;

import org.geppetto.core.common.GeppettoExecutionException;
import org.geppetto.core.model.ModelInterpreterException;
import org.geppetto.core.model.ModelWrapper;
import org.geppetto.core.model.RecordingModel;
import org.geppetto.core.services.GeppettoFeature;
import org.geppetto.core.simulator.GetAspectsVisitor;
import org.geppetto.core.utilities.RecordingReader;
import org.geppetto.model.values.Collada;

public class ColladaVisualTreeFeature implements IVisualTreeFeature
{

	GeppettoFeature type = GeppettoFeature.VISUAL_TREE_FEATURE;
	private Root root;

	@Override
	public GeppettoFeature getType()
	{
		return type;
	}

	@Override
	public boolean populateVisualTree(AspectNode aspectNode) throws ModelInterpreterException
	{
		Collada collada = new Collada("Collada");
		collada.setModel((String) ((ModelWrapper) aspectNode.getModel()).getModel("COLLADA"));

		aspectNode.getSubTree(AspectTreeType.VISUALIZATION_TREE).addChild(collada);
		((AspectSubTreeNode) aspectNode.getSubTree(AspectTreeType.VISUALIZATION_TREE)).setModified(true);

		// Populate visualization tree from recording (if any)
		this.populateVizTreeFromRecordings(aspectNode);

		return false;
	}

	private void populateVizTreeFromRecordings(AspectNode aspectNode) throws ModelInterpreterException
	{

		ModelWrapper model = (ModelWrapper) aspectNode.getModel();
		Collection<Object> models = model.getModels();
		Iterator i = models.iterator();
		while(i.hasNext())
		{
			Object m = i.next();
			if(m instanceof RecordingModel)
			{
				// Get scene root
				INode n = aspectNode.getParent();
				while(n.getParent() != null)
				{
					n = n.getParent();
				}

				this.root = (Root) n;
				// traverse scene root to get all simulation trees for all aspects
				GetAspectsVisitor mappingVisitor = new GetAspectsVisitor();
				this.root.apply(mappingVisitor);

				try
				{
					// we send the recording hdf5 location, plus map of aspects to populate them with recordings extracts
					RecordingReader recReader = new RecordingReader();
					recReader.populateVisualizationVariables(((RecordingModel) m).getHDF5().getAbsolutePath(), mappingVisitor.getAspects());
				}
				catch(GeppettoExecutionException e)
				{
					throw new ModelInterpreterException(e);
				}
			}
		}
	}
}