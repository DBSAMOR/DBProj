/*-
 * Copyright (C) 2002, 2017, Oracle and/or its affiliates. All rights reserved.
 *
 * This file was distributed by Oracle as part of a version of Oracle Berkeley
 * DB Java Edition made available at:
 *
 * http://www.oracle.com/technetwork/database/database-technologies/berkeleydb/downloads/index.html
 *
 * Please see the LICENSE file included in the top-level directory of the
 * appropriate version of Oracle Berkeley DB Java Edition for a copy of the
 * license and additional information.
 */

package com.sleepycat.persist.model;

/**
 * The metadata for a primary key field.  A primary key may be specified with
 * the {@link PrimaryKey} annotation.
 *
 * <p>{@code PrimaryKeyMetadata} objects are thread-safe.  Multiple threads may
 * safely call the methods of a shared {@code PrimaryKeyMetadata} object.</p>
 *
 * @author Mark Hayes
 */
public class PrimaryKeyMetadata extends FieldMetadata {

    private static final long serialVersionUID = 2946863622972437018L;

    private String sequenceName;

    /**
     * Used by an {@code EntityModel} to construct primary key metadata.
     *
     * @param name the field name.
     * @param className the class name.
     * @param declaringClassName the name of the class where the field is
     * declared.
     * @param sequenceName the sequence name.
     */
    public PrimaryKeyMetadata(String name,
                              String className,
                              String declaringClassName,
                              String sequenceName) {
        super(name, className, declaringClassName);
        this.sequenceName = sequenceName;
    }

    /**
     * Returns the name of the sequence for assigning key values.  This may be
     * specified using the {@link PrimaryKey#sequence} annotation.
     *
     * @return the sequence name.
     */
    public String getSequenceName() {
        return sequenceName;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof PrimaryKeyMetadata) {
            PrimaryKeyMetadata o = (PrimaryKeyMetadata) other;
            return super.equals(o) &&
                   ClassMetadata.nullOrEqual(sequenceName, o.sequenceName);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ClassMetadata.hashCode(sequenceName);
    }
}
