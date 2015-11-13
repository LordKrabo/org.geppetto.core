package org.geppetto.core.model.typesystem.values;

import java.util.ArrayList;
import java.util.List;

import org.geppetto.core.model.runtime.VisualObjectReferenceNode;
import org.geppetto.core.model.typesystem.ANode;
import org.geppetto.core.model.typesystem.visitor.IAnalysis;

/**
 * 
 * @author Jesus R. Martinez (jesus@metacell.us)
 *
 */
public class ConnectionValue extends ACompositeValue
{

	private String _entityInstancePath;
	private String _aspectInstancePath;
	// private ConnectionType _type;
	private List<ANode> _customNodes = new ArrayList<ANode>();
	private List<VisualObjectReferenceNode> _visualReferences = new ArrayList<VisualObjectReferenceNode>();

	public List<VisualObjectReferenceNode> getVisualReferences()
	{
		return _visualReferences;
	}

	public void setVisualReferences(List<VisualObjectReferenceNode> _visualReferences)
	{
		this._visualReferences = _visualReferences;
	}

	public List<ANode> getCustomNodes()
	{
		return _customNodes;
	}

	public void setCustomNodes(List<ANode> customProperties)
	{
		this._customNodes = customProperties;
	}

	public ConnectionValue(String id, AspectNode aspectNode)
	{
		super(id);
		if(aspectNode != null)
		{
			_aspectInstancePath = aspectNode.getInstancePath();
		}
	}

	public void setEntityInstancePath(String entityId)
	{
		this._entityInstancePath = entityId;
	}

	@Override
	public String getAspectInstancePath()
	{
		return _aspectInstancePath;
	}

	public String getEntityInstancePath()
	{
		return this._entityInstancePath;
	}

	@Override
	public boolean apply(IAnalysis visitor)
	{
		if(visitor.inConnectionNode(this))
		{
			for(ANode a : this.getCustomNodes())
			{
				a.apply(visitor);
				if(visitor.stopVisiting())
				{
					break;
				}
			}

			for(VisualObjectReferenceNode vis : this.getVisualReferences())
			{
				vis.apply(visitor);
				if(visitor.stopVisiting())
				{
					break;
				}
			}

		}
		return visitor.outConnectionNode(this);
	}
}