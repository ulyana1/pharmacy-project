package com.pharmacy.view.elements;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pharmacy.annotations.TableColumn;
import com.pharmacy.annotations.Tableable;

public final class CustomTableModel<EType> extends AbstractTableModel {
    private static final long serialVersionUID = -8974194096182747278L;
    
    private List<EType> data;
    private Class<EType> elemType;
    private List<Column> columns;
    
    public CustomTableModel(List<EType> entities, Class<EType> clazz) {
        this(entities, clazz, null);
    }
    
    public CustomTableModel(List<EType> entities, Class<EType> clazz, TableSelectionEventHandler<EType> handler) {
        elemType = clazz;
        data = entities;
        columns = new ArrayList<>();
        
        if (elemType.isAnnotationPresent(Tableable.class)) {
            Method[] methods = elemType.getMethods();
            for (Method m : methods) {
                TableColumn c = m.getAnnotation(TableColumn.class);
                
                if (c != null) {
                    columns.add(new Column(c.number(), c.name(), m));
                }
            }
        }
        
        Collections.sort(columns);
    }
    
    private class Column implements Comparable<Column>{
        public final int position;
        public final String name;
        public final Method method;
        
        public Column(int columnPos, String columnName, Method toInvoke) {
            this.position = columnPos;
            this.name = columnName;
            this.method = toInvoke;
        }

        @Override
        public int compareTo(CustomTableModel<EType>.Column o) {
            if (o == null) {
                return 1;
            }
            
            return position - o.position;
        }
    }
    
    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col).name;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            Object res = columns.get(col).method.invoke(data.get(row));
            if (res == null) {
                return null;
            }
            return res;
        } catch (Exception ignored) { }
        return "Error";
    }
    
    @Override
    public Class getColumnClass(int c) {
        Object o = getValueAt(0, c);
        if (o == null) {
            return String.class;
        }
        return o.getClass();
    }
    
    public EType getAtRow(int row) {
        if (row < 0 || row > data.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        return data.get(row);
    }
   
    public interface TableSelectionEventHandler<EType> {
        public void handle(EType object, int row);
    }
}
