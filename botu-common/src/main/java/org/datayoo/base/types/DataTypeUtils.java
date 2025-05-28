package org.datayoo.base.types;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 */
public abstract class DataTypeUtils {
  // 对象
  public static final char BEGIN_O_ENCLOSURE = '{';

  public static final char END_O_ENCLOSURE = '}';

  public static final String HEX_FORMAT = "x";

  public static final String OCTAL_FORMAT = "o";

  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static final String NULL = "NULL";

  public static final DataType NULL_TYPE = new NullType();
  public static final DataType INTEGER_TYPE = new IntegerType();
  public static final DataType LONG_TYPE = new LongType();
  public static final DataType FLOAT_TYPE = new FloatType();
  public static final DataType DOUBLE_TYPE = new DoubleType();
  public static final DataType BOOLEAN_TYPE = new BooleanType();
  public static final DataType STRING_TYPE = new StringType();
  public static final DataType TIMESTAMP_TYPE = new DatetimeType();
  public static final DataType OBJECT_TYPE = new ObjectType();
  public static final DataType DEFAULT_MAP_TYPE = new MapType(
      new DataType[] { STRING_TYPE, OBJECT_TYPE });

  public static Date createDatetime(String date, String format) {
    date = preDealConvertedString(date);
    if (date == null)
      return null;
    SimpleDateFormat formater = null;
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    formater = new SimpleDateFormat(format);
    try {
      return formater.parse(date);
    } catch (ParseException e) {
      try {
        long millionSeconds = Long.valueOf(date).longValue();
        return new Date(millionSeconds);
      } catch (Exception e1) {
        throw new TypeConversionException(e1);
      }
    }
  }

  protected static String preDealConvertedString(String data) {
    if (data != null)
      data = data.trim();
    if (StringUtils.isEmpty(data))
      return null;
    return data;
  }

  public static Object convertTo(Object data, DataTypeName toTypeName,
      String toFormat) {
    if (toTypeName == null)
      return data;
    if (data == null || data.equals(NULL))
      return null;
    try {
      if (toTypeName.equals(DataTypeName.Integer)) {
        return convert2Integer(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Long)) {
        return convert2Long(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Float)) {
        return convert2Float(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Double)) {
        return convert2Double(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Boolean)) {
        return convert2Boolean(data);
      } else if (toTypeName.equals(DataTypeName.String)) {
        return toString(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Datetime)) { // Date,Time
        return convert2Datetime(data, toFormat);
      } else if (toTypeName.equals(DataTypeName.Map)) {
        if (data instanceof Map)
          return data;
      } else if (toTypeName.equals(DataTypeName.Object)) {
        return data;
      } else {
        throw new UnsupportedOperationException(
            String.format("Can not convert '%s' to '%s'!",
                data.getClass().getName(), toTypeName.name()));
      }
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
    return data;
  }

  public static Integer convert2Integer(Object data, String toFormat) {
    if (data instanceof Number) {
      return ((Number) data).intValue();
    } else if (data instanceof String) {
      return Integer.valueOf((String) data);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Integer'!",
            data.getClass().getName()));
  }

  public static Long convert2Long(Object data, String toFormat) {
    if (data instanceof Number) {
      return ((Number) data).longValue();
    } else if (data instanceof String) {
      return Long.valueOf((String) data);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Long'!",
            data.getClass().getName()));
  }

  public static Float convert2Float(Object data, String toFormat) {
    if (data instanceof Number) {
      return ((Number) data).floatValue();
    } else if (data instanceof String) {
      return Float.valueOf((String) data);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Float'!",
            data.getClass().getName()));
  }

  public static Double convert2Double(Object data, String toFormat) {
    if (data instanceof Number) {
      return ((Number) data).doubleValue();
    } else if (data instanceof String) {
      return Double.valueOf((String) data);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Double'!",
            data.getClass().getName()));
  }

  public static Boolean convert2Boolean(Object data) {
    if (data instanceof Boolean) {
      return (Boolean) data;
    } else if (data instanceof String) {
      return Boolean.valueOf((String) data);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Boolean'!",
            data.getClass().getName()));
  }

  public static Date convert2Datetime(Object data, String toFormat) {
    if (data instanceof Date) {
      return (Date) data;
    } else if (data instanceof String) {
      return createDatetime((String) data, toFormat);
    }
    throw new UnsupportedOperationException(
        String.format("Can not convert '%s' to 'Datetime'!",
            data.getClass().getName()));
  }

  public static String toString(Object value, String toFormat) {
    if (value == null) {
      return NULL;
    }
    if (value instanceof Date) {
      return toDatetimeString((Date) value, toFormat);
    }
    return value.toString();
  }

  public static String toDatetimeString(Date date, String format) {
    SimpleDateFormat formater = null;
    if (format == null) {
      format = DEFAULT_DATE_FORMAT;
    }
    formater = new SimpleDateFormat(format);
    return formater.format(date);
  }

  public static int intValue(String number) {
    int radix = getRadix(number);
    if (radix == 16)
      number = number.substring(2);
    else if (radix == 8)
      number = number.substring(1);
    return Integer.valueOf(number, radix).intValue();
  }

  protected static int getRadix(String number) {
    if (number.indexOf("0x") != -1 || number.indexOf("0X") != -1)
      return 16;
    if (number.indexOf("o") != -1 || number.indexOf("O") != -1)
      return 8;
    return 10;
  }
}
