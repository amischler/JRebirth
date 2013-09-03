package org.jrebirth.core.util;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ObjectUtilTest</strong>.
 * 
 * @author Sébastien Bordes
 */
public class ObjectUtilTest {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtilTest.class);

    @Test
    public void equalsOrBothNull() {

        String na = null;
        String nb = null;

        String a = "a_string";
        String a2 = "a_string";

        String b = "b_string";

        Assert.assertTrue("Null <=> Null", ObjectUtility.equalsOrBothNull(na, nb));

        Assert.assertFalse("A <=> Null", ObjectUtility.equalsOrBothNull(a, nb));

        Assert.assertFalse("Null <=> B", ObjectUtility.equalsOrBothNull(na, b));

        Assert.assertFalse("A <=> B", ObjectUtility.equalsOrBothNull(a, b));
        Assert.assertFalse("B <=> A", ObjectUtility.equalsOrBothNull(b, a));

        Assert.assertTrue("A <=> A'", ObjectUtility.equalsOrBothNull(a, a2));
        Assert.assertFalse("B <=> A", ObjectUtility.equalsOrBothNull(b, a));
        Assert.assertFalse("B <=> A'", ObjectUtility.equalsOrBothNull(b, a2));

    }

    @Test
    public void notEquals() {

        String na = null;
        String nb = null;

        String a = "a_string";
        String a2 = "a_string";

        String b = "b_string";

        Assert.assertFalse("Null </=/> Null", ObjectUtility.notEquals(na, nb));

        Assert.assertTrue("A </=/> Null", ObjectUtility.notEquals(a, nb));

        Assert.assertTrue("Null </=/> B", ObjectUtility.notEquals(na, b));

        Assert.assertTrue("A </=/> B", ObjectUtility.notEquals(a, b));
        Assert.assertTrue("B </=/> A", ObjectUtility.notEquals(b, a));

        Assert.assertFalse("A </=/> A'", ObjectUtility.notEquals(a, a2));
        Assert.assertTrue("B </=/> A", ObjectUtility.notEquals(b, a));
        Assert.assertTrue("B </=/> A'", ObjectUtility.notEquals(b, a2));

    }
}
