package com.service.library;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class CommonUtil {

    private static final Log LOG = LogFactory.getLog(CommonUtil.class);

    private CommonUtil() {
	super();
    }

    public static String trimExpression(String expression) {
	if (expression.charAt(0) == '$') {
            return expression.substring(2, expression.length() - 1);
	} else {
            return expression;
	}
    }

    @SuppressWarnings("unchecked")
    public static Object getValue(Object source, String property) {
        Object value = null;
	if (source != null && StringUtils.isNotEmpty(property)) {
            if (source instanceof Map<?, ?>) {
		Map<String, Object> map = (Map<String, Object>) source;
		value = map.get(property);
            } else {
                try {
                    value = PropertyUtils.getProperty(source, property);
                } catch (java.lang.Exception e) {
                    LOG.error("Error when getting property: " + property
                            + " from source: " + source, e);
                }
            }
        }
	return value;
    }

    public static String formatValue(Object value) {
	String result = "";
	if (value == null) {
            return result;
        }
	if (value instanceof Date) {
            result = DateUtil.formatDate((Date) value);
	} else {
            result = value.toString();
	}
        return result;
    }

    public static String checkNullString(String string) {
        if (string == null) {
            return "";
	}
	return string;
    }

    public static String checkNullString(Object object) {
        if (object == null) {
            return "";
	}
	return object.toString();
    }

    public static Integer checkNullInteger(Integer integer) {
        if (integer == null) {
            return Integer.valueOf(0);
	}
	return integer;
    }

    public static boolean checkEquality(Object object, Object... objectsToCompare) {
	if (object == null) {
            return false;
	}
	if (objectsToCompare == null || objectsToCompare.length == 0) {
            throw new IllegalArgumentException("ObjectsToCompare cannot be null or empty.");
	}
	for (Object objectToCompare : objectsToCompare) {
            if (objectToCompare == null) {
		continue;
            }
            if (object.equals(objectToCompare)) {
		return true;
            }
	}
	return false;
    }

    public static boolean checkEqualityIgnoreCase(String string,
	String... stringsToCompare) {
	if (string == null) {
            throw new IllegalArgumentException("String cannot be null.");
	}
	if (stringsToCompare == null || stringsToCompare.length == 0) {
            throw new IllegalArgumentException(
		"StringsToCompare cannot be null or empty.");
	}
	for (String stringToCompare : stringsToCompare) {
            if (string.equalsIgnoreCase(stringToCompare)) {
		return true;
            }
	}
	return false;
    }
	
    public static Boolean isNotNullOrEmpty(Object object) {
	return !isNullOrEmpty(object);
    }
	    
    @SuppressWarnings("rawtypes")
    public static Boolean isNullOrEmpty(Object object) {
	if (object == null) {
	    return true;
	} else {
	if (object instanceof Collection) {
            if (((Collection) object).isEmpty()) {
	        return true;
	    }
	} else if (object instanceof AbstractMap) {
	    if (((AbstractMap) object).isEmpty()) {
	        return true;
	    }
	} else {
	    if (object.toString().trim().equals("")) {
	        return true;
	    }
	}
            return false;
	}
    }
}

