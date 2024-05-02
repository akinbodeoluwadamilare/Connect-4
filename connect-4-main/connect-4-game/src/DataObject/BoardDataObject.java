package DataObject;

import DomainObjects.BoardDomainObject;

public class BoardDataObject {
    public int rows;
    public int columns;

    public BoardDataObject(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public BoardDataObject(BoardDomainObject bDomainObject) {
        this.rows = bDomainObject.GetRows();
        this.columns = bDomainObject.GetColumns();
    }
}
