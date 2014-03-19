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
package com.vaadin.client.ui.grid;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Test;

import com.vaadin.client.data.DataChangeHandler;
import com.vaadin.client.ui.grid.datasources.ListDataSource;

/**
 * 
 * @since 7.2
 * @author Vaadin Ltd
 */
public class ListDataSourceTest {

    @Test
    public void testDataSourceConstruction() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        assertEquals(4, ds.getEstimatedSize());
        assertEquals(0, (int) ds.getRow(0));
        assertEquals(1, (int) ds.getRow(1));
        assertEquals(2, (int) ds.getRow(2));
        assertEquals(3, (int) ds.getRow(3));

        ds = new ListDataSource<Integer>(Arrays.asList(0, 1, 2, 3));

        assertEquals(4, ds.getEstimatedSize());
        assertEquals(0, (int) ds.getRow(0));
        assertEquals(1, (int) ds.getRow(1));
        assertEquals(2, (int) ds.getRow(2));
        assertEquals(3, (int) ds.getRow(3));
    }

    @Test
    public void testListAddOperation() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        DataChangeHandler handler = EasyMock
                .createNiceMock(DataChangeHandler.class);
        ds.setDataChangeHandler(handler);

        handler.dataAdded(4, 1);
        EasyMock.expectLastCall();

        EasyMock.replay(handler);

        ds.asList().add(4);

        assertEquals(5, ds.getEstimatedSize());
        assertEquals(0, (int) ds.getRow(0));
        assertEquals(1, (int) ds.getRow(1));
        assertEquals(2, (int) ds.getRow(2));
        assertEquals(3, (int) ds.getRow(3));
        assertEquals(4, (int) ds.getRow(4));
    }

    @Test
    public void testListAddAllOperation() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        DataChangeHandler handler = EasyMock
                .createNiceMock(DataChangeHandler.class);
        ds.setDataChangeHandler(handler);

        handler.dataAdded(4, 3);
        EasyMock.expectLastCall();

        EasyMock.replay(handler);

        ds.asList().addAll(Arrays.asList(4, 5, 6));

        assertEquals(7, ds.getEstimatedSize());
        assertEquals(0, (int) ds.getRow(0));
        assertEquals(1, (int) ds.getRow(1));
        assertEquals(2, (int) ds.getRow(2));
        assertEquals(3, (int) ds.getRow(3));
        assertEquals(4, (int) ds.getRow(4));
        assertEquals(5, (int) ds.getRow(5));
        assertEquals(6, (int) ds.getRow(6));
    }

    @Test
    public void testListRemoveOperation() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        DataChangeHandler handler = EasyMock
                .createNiceMock(DataChangeHandler.class);
        ds.setDataChangeHandler(handler);

        handler.dataRemoved(3, 1);
        EasyMock.expectLastCall();

        EasyMock.replay(handler);

        ds.asList().remove(2);

        assertEquals(3, ds.getEstimatedSize());
        assertEquals(0, (int) ds.getRow(0));
        assertEquals(1, (int) ds.getRow(1));
        assertEquals(3, (int) ds.getRow(2));
    }

    @Test
    public void testListRemoveAllOperation() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        DataChangeHandler handler = EasyMock
                .createNiceMock(DataChangeHandler.class);
        ds.setDataChangeHandler(handler);

        handler.dataRemoved(0, 3);
        EasyMock.expectLastCall();

        EasyMock.replay(handler);

        ds.asList().removeAll(Arrays.asList(0, 2, 3));

        assertEquals(1, ds.getEstimatedSize());
        assertEquals(1, (int) ds.getRow(0));
    }

    @Test
    public void testListClearOperation() throws Exception {

        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);

        DataChangeHandler handler = EasyMock
                .createNiceMock(DataChangeHandler.class);
        ds.setDataChangeHandler(handler);

        handler.dataRemoved(0, 4);
        EasyMock.expectLastCall();

        EasyMock.replay(handler);

        ds.asList().clear();

        assertEquals(0, ds.getEstimatedSize());
    }

    @Test(expected = IllegalStateException.class)
    public void testFetchingNonExistantItem() {
        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);
        ds.ensureAvailability(5, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedIteratorRemove() {
        ListDataSource<Integer> ds = new ListDataSource<Integer>(0, 1, 2, 3);
        ds.asList().iterator().remove();
    }

}