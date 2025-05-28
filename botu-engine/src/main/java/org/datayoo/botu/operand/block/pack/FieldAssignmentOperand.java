package org.datayoo.botu.operand.block.pack;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.event.FieldSerializationMetadata;
import org.datayoo.botu.event.GeneralArrayFieldFiller;
import org.datayoo.botu.exception.BotuRuntimeException;
import org.datayoo.botu.metadata.ExpressionMetadata;
import org.datayoo.botu.operand.AbstractProcessOperand;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.types.TypeWrapper;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.Operand;

public class FieldAssignmentOperand extends AbstractProcessOperand {

  protected GeneralArrayFieldFiller fieldFiller;

  protected FieldSerializationMetadata fieldSerializationMetadata;

  protected TypeWrapper typeWrapper;

  protected Operand valueOperand;

  public FieldAssignmentOperand(FieldSerializationMetadata metadata,
      TypeWrapper typeWrapper, Operand valueOperand) {
    Validate.notNull(metadata, "metadata is null!");
    Validate.notNull(typeWrapper, "typeWrapper is null!");
    Validate.notNull(valueOperand, "valueOperand is null!");

    fieldFiller = new GeneralArrayFieldFiller(metadata);
    this.fieldSerializationMetadata = metadata;
    this.typeWrapper = typeWrapper;
    this.valueOperand = valueOperand;
  }

  public void operate(EntityMap entityMap, Object[] event) {
    Object value = valueOperand.operate(entityMap);
    if (value != null) {
      value = typeWrapper.wrapper(value);
      fieldFiller.put(value, event);
    }
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    Object value = valueOperand.operate(entityMap);
    if (value != null) {
      try {
        return typeWrapper.wrapper(value);
      } catch (Throwable t) {
        ExpressionMetadata expressionMetadata = (ExpressionMetadata) source;
        throw new BotuRuntimeException(
            String.format("Format '%s' of field %s failed! At line %d",
                value.toString(), fieldFiller.getName(),
                expressionMetadata.getLineNo()), t);
      }
    }
    return value;
  }

  public String getFieldName() {
    return fieldFiller.getName();
  }

  public Operand getValueOperand() {
    return valueOperand;
  }

  public FieldSerializationMetadata getFieldSerializationMetadata() {
    return fieldSerializationMetadata;
  }
}
