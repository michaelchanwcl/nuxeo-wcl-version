/*
 * (C) Copyright 2006-2011 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.ecm.core.api.security;

import java.io.Serializable;
import java.util.List;

/**
 * An ACL (Access Control List) is a list of ACEs (Access Control Entry).
 * <p>
 * An ACP may contain several ACL identified by a name. This is to let external modules add security rules. There are 2
 * default ACLs:
 * <ul>
 * <li>the <code>local</code> ACL - this is the default type of ACL that may be defined by an user locally to a document
 * (using a security UI). <br>
 * This is the only ACL an user can change
 * <li>the <code>inherited</code> - this is a special ACL generated by merging all document parents ACL. This ACL is
 * read only (cannot be modified locally on the document since it is inherited.
 * </ul>
 * ACLs that are used by external modules cannot be modified by the user through the security UI. These ACLs should be
 * modified only programmatically by the tool that added them.
 *
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 */
public interface ACL extends List<ACE>, Serializable, Cloneable {

    String LOCAL_ACL = "local";

    String INHERITED_ACL = "inherited";

    /**
     * Gets the ACL name.
     *
     * @return the ACL name
     */
    String getName();

    /**
     * Returns the ACEs defined by this list as an array.
     */
    ACE[] getACEs();

    /**
     * Sets the ACEs defined by this ACL.
     *
     * @param aces the ACE array
     */
    void setACEs(ACE[] aces);

    /**
     * Block the inheritance.
     *
     * @param username the user blocking the inheritance
     * @return true if the ACL was changed.
     * @since 7.4
     */
    boolean blockInheritance(String username);

    /**
     * Unblock the inheritance.
     *
     * @return true if the ACL was changed.
     * @since 7.4
     */
    boolean unblockInheritance();

    /**
     * Add an ACE.
     *
     * @return true if the ACL was changed.
     * @since 7.4
     */
    boolean add(ACE ace);

    /**
     * Replace the {@code oldACE} with {@code newACE}, only if the {@code oldACE} exists.
     * <p>
     * The {@code newACE} keeps the same index as {@code oldACE}.
     *
     * @return true if the ACL was changed.
     * @since 7.4
     */
    boolean replace(ACE oldACE, ACE newACE);

    /**
     * Remove all ACEs for {@code username}.
     *
     * @return true if the ACL was changed.
     * @since 7.4
     */
    boolean removeByUsername(String username);

    /**
     * Returns a recursive copy of the ACL sharing no mutable substructure with the original.
     *
     * @return a copy
     */
    Object clone();

    /**
     * Replaces a permission with another in this ACL.
     *
     * @param oldPerm the old permission
     * @param newPerm the new permission
     * @since 11.3
     */
    void replacePermission(String oldPerm, String newPerm);

}
