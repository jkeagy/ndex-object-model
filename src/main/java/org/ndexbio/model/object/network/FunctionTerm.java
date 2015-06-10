/**
 *   Copyright (c) 2013, 2015
 *  	The Regents of the University of California
 *  	The Cytoscape Consortium
 *
 *   Permission to use, copy, modify, and distribute this software for any
 *   purpose with or without fee is hereby granted, provided that the above
 *   copyright notice and this permission notice appear in all copies.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 *   WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 *   ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 *   WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 *   ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 *   OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package org.ndexbio.model.object.network;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionTerm extends Term implements Comparable <FunctionTerm> 
{
    private long _functionTermId;
    
    // element id list of other terms
    private List<Long> _parameterIds;

    
    
    /**************************************************************************
    * Default constructor.
    **************************************************************************/
    public FunctionTerm()
    {
        super();
        _type = this.getClass().getSimpleName();
        this._parameterIds = new ArrayList<>(10);
    }

    public List<Long> getParameterIds()
    {
        return _parameterIds;
    }

    public void setParameterIds(List<Long> parameters)
    {
        _parameterIds = parameters;
    }


	public long getFunctionTermId() {
		return _functionTermId;
	}

	public void setFunctionTermId(long functionTermId) {
		this._functionTermId = functionTermId;
	}

	@Override
	public int compareTo(FunctionTerm o) {
        if ( getId() > 0 ) {
        	long c = getId() - o.getId();
        	if ( c==0) return 0 ;
        	return c > 0? 1 : -1;
        }
		
        if (o.getId() > 0) return -1;
        
        // compare the contents
        long c = _functionTermId - o.getFunctionTermId();
        if ( c != 0 )
        	return c>0 ? 1 : -1;
        
        List<Long> p2 = o.getParameterIds();
        int ci = _parameterIds.size() - p2.size();
        
        if (ci != 0) return ci;
        
        for ( int i = 0 ; i < _parameterIds.size(); i++ ) {
        	c = _parameterIds.get(i).longValue() - p2.get(i).longValue();
        	if ( c !=0) return c>0? 1 : -1; 
        }     
        
        return 0;
	}
}
