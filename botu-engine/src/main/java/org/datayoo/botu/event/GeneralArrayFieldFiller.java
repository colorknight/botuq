package org.datayoo.botu.event;

import org.apache.commons.lang3.Validate;
import org.datayoo.base.types.DataTypeName;
import org.datayoo.base.types.DataTypeUtils;

public class GeneralArrayFieldFiller {

  protected FieldSerializationMetadata metadata;

  protected int index;

  public GeneralArrayFieldFiller(FieldSerializationMetadata metadata) {
    Validate.notNull(metadata, "metadata is null!");
    this.metadata = metadata;
    index = metadata.getIndex() + 1;  // index 0 is always schema name.
  }

  public GeneralArrayFieldFiller(SerializationSchema schema, String fieldName) {
    Validate.notNull(schema, "schema is null!");
    Validate.notEmpty(fieldName, "fieldName is empty!");

    FieldSerializationMetadata metadata = schema.getMetadataMap()
        .get(fieldName);
    if (metadata == null) {
      throw new IllegalArgumentException(
          String.format("Field '%s' doesn't exist in schema '%s'!", fieldName,
              schema.getName()));
    }
    this.metadata = metadata;
    index = metadata.getIndex() + 1;  // index 0 is always schema name.
  }

  public String getName() {
    return metadata.getName();
  }

  public void put(Object object, Object[] objects) {
    if (object != null) {
      try {
        object = DataTypeUtils.convertTo(object, metadata.getDataType(),
            metadata.getDataFormat());
      } catch (Throwable t) {
        throw new RuntimeException(
            String.format("Field '%s' type '%s:%s' convert failed!",
                metadata.getName(), metadata.getDataType(),
                metadata.getBizType()), t);
      }
    }
    objects[index] = object;
  }

  public Object get(Object[] objects) {
    return objects[index];
  }

  public void unsafePut(Object object, Object[] objects) {
    objects[index] = object;
  }

  public DataTypeName getDataType() {
    return metadata.getDataType();
  }

  public String getBizType() {
    return metadata.getBizType();
  }

  public String getDataFormat() {
    return metadata.getDataFormat();
  }
}
