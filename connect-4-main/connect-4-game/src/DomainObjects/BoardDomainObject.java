package DomainObjects;

import DataObject.BoardDataObject;

public class BoardDomainObject {
    public int rows;
    public int columns;

    public BoardDomainObject(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public BoardDomainObject(BoardDataObject boardObject) {
        this.rows = boardObject.rows;
        this.columns = boardObject.columns;
    }

    public int GetRows() {
        return this.rows;
    }

    public int GetColumns() {
        return this.columns;
    }
}
