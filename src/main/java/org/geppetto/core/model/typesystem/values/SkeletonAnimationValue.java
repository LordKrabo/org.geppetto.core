/*******************************************************************************
 * The MIT License (MIT)
 * 
 * Copyright (c) 2011 - 2015 OpenWorm.
 * http://openworm.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *     	OpenWorm - http://openworm.org/people.html
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE 
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package org.geppetto.core.model.typesystem.values;

import java.util.ArrayList;
import java.util.List;

import org.geppetto.core.model.typesystem.visitor.IAnalysis;

/**
 * A node that stores skeleton animations.
 * 
 * @author Giovanni Idili (giovanni@openworm.org)
 *
 */
public class SkeletonAnimationValue extends AValue
{

	/**
	 * _skeletonAnimationMatrices stores a list of bidimensional matrices with skeleton animation info.
	 * 
	 * TODO: Look into refactoring this into a generic MatrixValue, same as the other AValue derived classes we have.
	 */
	private List<List<Double>> skeletonTransformationSeries = new ArrayList<List<Double>>();;

	public SkeletonAnimationValue(String id)
	{
		super(id);
	}

	@Override
	public boolean apply(IAnalysis visitor)
	{
		return visitor.visitSkeletonAnimationNode(this);
	}

	public List<List<Double>> getSkeletonTransformationSeries()
	{
		return skeletonTransformationSeries;
	}

	public void setSkeletonTransformationSeries(List<List<Double>> skeletonAnimationMatrices)
	{
		this.skeletonTransformationSeries = skeletonAnimationMatrices;
	}

	public void addSkeletonTransformation(List<Double> skeletonTransformation)
	{
		this.skeletonTransformationSeries.add(skeletonTransformation);
	}
}