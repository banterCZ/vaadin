/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vaadin.shared.ui.grid;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.shared.AbstractComponentState;

/**
 * The shared state for the {@link com.vaadin.ui.components.grid.Grid} component
 * 
 * @since 7.2
 * @author Vaadin Ltd
 */
public class GridState extends AbstractComponentState {
    {
        // FIXME Grid currently does not support undefined size
        width = "400px";
        height = "400px";
    }

    /**
     * Columns in grid. Column order implicitly deferred from list order.
     */
    public List<GridColumnState> columns = new ArrayList<GridColumnState>();

    /**
     * Is the column header row visible
     */
    public boolean columnHeadersVisible = true;

    /**
     * Is the column footer row visible
     */
    public boolean columnFootersVisible = false;

    /**
     * The column groups added to the grid
     */
    public List<ColumnGroupRowState> columnGroupRows = new ArrayList<ColumnGroupRowState>();

    /**
     * The id for the last frozen column.
     * 
     * @see GridColumnState#id
     */
    public String lastFrozenColumnId = null;

}