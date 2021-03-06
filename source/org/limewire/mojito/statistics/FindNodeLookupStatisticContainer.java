/*
 * Mojito Distributed Hash Table (Mojito DHT)
 * Copyright (C) 2006-2007 LimeWire LLC
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
 
package org.limewire.mojito.statistics;

import org.limewire.mojito.Context;
import org.limewire.mojito.KUID;
import org.limewire.statistic.Statistic;

public class FindNodeLookupStatisticContainer extends SingleLookupStatisticContainer {
    
    /**
     * <tt>Statistic</tt> for all outgoing lookup messages for this lookup.
     */
    public Statistic FIND_NODE_LOOKUP_REQUESTS = new SimpleStatistic();
    
    /**
     * <tt>Statistic</tt> for all incoming lookup messages for this lookup.
     */
    public Statistic FIND_NODE_LOOKUP_REPLIES = new SimpleStatistic();
    
    /**
     * <tt>Statistic</tt> for timeouts for this lookup.
     */
    public Statistic FIND_NODE_LOOKUP_TIMEOUTS= new SimpleStatistic();
    
    /**
     * <tt>Statistic</tt> for the number of hops for this lookup.
     */
    public Statistic FIND_NODE_LOOKUP_HOPS = new SimpleStatistic();
    
    /**
     * <tt>Statistic</tt> for the time of this lookup.
     */
    public Statistic FIND_NODE_LOOKUP_TIME = new SimpleStatistic();
    
    public FindNodeLookupStatisticContainer(Context context, KUID lookupKey) {
        super(context, lookupKey);
    }
    
    public void setHops(int hops) {
        super.setHops(hops, false);
        FIND_NODE_LOOKUP_HOPS.addData(hops);
        FIND_NODE_LOOKUP_HOPS.storeCurrentStat();
    }
    
    public void setTime(int time) {
        super.setTime(time, false);
        FIND_NODE_LOOKUP_TIME.addData(time);
        FIND_NODE_LOOKUP_TIME.storeCurrentStat();
    }


    @Override
    public void addReply() {
        super.addReply();
        FIND_NODE_LOOKUP_REPLIES.incrementStat();
    }


    @Override
    public void addRequest() {
        super.addRequest();
        FIND_NODE_LOOKUP_REQUESTS.incrementStat();
    }


    @Override
    public void addTimeout() {
        super.addTimeout();
        FIND_NODE_LOOKUP_TIMEOUTS.incrementStat();
    }

}
