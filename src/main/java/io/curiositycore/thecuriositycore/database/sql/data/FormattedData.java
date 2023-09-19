package io.curiositycore.thecuriositycore.database.sql.data;

public interface FormattedData<T> {
    String getFormattedQuerySegment();
    boolean correctDataKey(FormattedData<?> formattedData);
    void updateData(FormattedData<?> dataToUpdateTo);
    String getDataKey();
    T getDataValue();
    void setDataKey(String keyToSet);
}
