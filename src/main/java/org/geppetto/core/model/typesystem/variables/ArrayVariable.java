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
package org.geppetto.core.model.typesystem.variables;

import java.util.Collection;

import org.geppetto.core.library.GeppettoTypeException;
import org.geppetto.core.model.typesystem.types.IType;
import org.geppetto.core.model.typesystem.values.IValue;

/**
 * @author matteocantarelli
 *
 */
public class ArrayVariable implements IVariable
{

	public ArrayVariable(String name, IType type)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<IType> getTypes()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addType(IType type)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public IValue getInitialValue(IType type) throws GeppettoTypeException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IType getType() throws GeppettoTypeException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IValue getInitialValue() throws GeppettoTypeException
	{
		// TODO Auto-generated method stub
		return null;
	}

}