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

package org.geppetto.core.data.model.local;

import java.util.Date;
import java.util.List;

import org.geppetto.core.data.model.IExperiment;

public class LocalExperiment implements IExperiment
{
	private long id;

	private String name;

	private String description;

	private Date creationDate;

	private Date lastModified;

	private List<LocalParameter> modelParameters;

	private List<LocalSimulationRun> simulationRuns;

	public LocalExperiment(String name, String description, Date creationDate, Date lastModified, List<LocalParameter> modelParameters, List<LocalSimulationRun> simulationRuns)
	{
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.lastModified = lastModified;
		this.modelParameters = modelParameters;
		this.simulationRuns = simulationRuns;
	}

	@Override
	public long getId()
	{
		return id;
	}

	@Override
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public Date getCreationDate()
	{
		return creationDate;
	}

	@Override
	public Date getLastModified()
	{
		return lastModified;
	}

	@Override
	public List<LocalParameter> getModelParameters()
	{
		return modelParameters;
	}

	@Override
	public List<LocalSimulationRun> getSimulationRuns()
	{
		return simulationRuns;
	}
}