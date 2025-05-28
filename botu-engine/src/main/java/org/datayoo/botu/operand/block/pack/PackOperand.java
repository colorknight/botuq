package org.datayoo.botu.operand.block.pack;

import org.apache.commons.lang3.Validate;
import org.datayoo.botu.BotuListener;
import org.datayoo.botu.event.SerializationSchema;
import org.datayoo.botu.operand.JumpMode;
import org.datayoo.botu.operand.StateContext;
import org.datayoo.botu.operand.block.AbstractCodeBlcokOperand;
import org.datayoo.moql.EntityMap;
import org.datayoo.moql.EntityMapImpl;

import java.util.LinkedList;
import java.util.List;

public class PackOperand extends AbstractCodeBlcokOperand {

  public static final String FIELD_SCHEMA = "FIELD_SCHEMA";

  protected SerializationSchema serializationSchema;

  protected boolean mapMode = true;

  protected BotuListener botuListener;

  protected List<FieldAssignmentOperand> assignmentOperands = new LinkedList<FieldAssignmentOperand>();

  public PackOperand() {
  }

  public PackOperand(SerializationSchema serializationSchema, boolean mapMode,
      BotuListener botuListener) {
    Validate.notNull(serializationSchema, "serializationSchema is null!");
    Validate.notNull(botuListener, "botuListener is null!");
    this.serializationSchema = serializationSchema;
    this.mapMode = mapMode;
    this.botuListener = botuListener;
  }

  @Override
  public Object operate(EntityMap entityMap, StateContext stateContext) {
    if (serializationSchema == null) {
      stateContext.setJumpMode(JumpMode.CONTINUE);
      return null;
    }
    if (mapMode) {
      packMap(entityMap, stateContext);
    } else {
      packArray(entityMap, stateContext);
    }
    return null;
  }

  protected void packMap(EntityMap entityMap, StateContext stateContext) {
    EntityMap event = new EntityMapImpl();
    stateContext.setResult(event);
    event.putEntity(FIELD_SCHEMA, serializationSchema.getName());
    try {
      for (FieldAssignmentOperand assignmentOperand : assignmentOperands) {
        Object value = assignmentOperand.operate(entityMap, stateContext);
        if (value != null) {
          entityMap.putEntity(assignmentOperand.getFieldName(), value);
          event.putEntity(assignmentOperand.getFieldName(), value);
        }
      }
      botuListener.onData(event);
    } finally {
      stateContext.setJumpMode(JumpMode.CONTINUE);
    }
  }

  protected void packArray(EntityMap entityMap, StateContext stateContext) {
    Object[] event = new Object[serializationSchema.getMetadataList().size()
        + 1];
    event[0] = serializationSchema.getName();
    try {
      for (FieldAssignmentOperand assignmentOperand : assignmentOperands) {
        assignmentOperand.operate(entityMap, event);
      }
      botuListener.onData(event);
    } finally {
      stateContext.setJumpMode(JumpMode.CONTINUE);
    }
  }

  public List<FieldAssignmentOperand> getAssignmentOperands() {
    return assignmentOperands;
  }

  public void setAssignmentOperands(
      List<FieldAssignmentOperand> assignmentOperands) {
    if (assignmentOperands != null)
      this.assignmentOperands = assignmentOperands;
  }

  public boolean isMapMode() {
    return mapMode;
  }

  public SerializationSchema getSerializationSchema() {
    return serializationSchema;
  }

  public BotuListener getBotuListener() {
    return botuListener;
  }
}
