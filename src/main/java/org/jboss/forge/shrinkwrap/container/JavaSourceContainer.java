/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.container;

import org.jboss.forge.roaster.model.JavaType;
import org.jboss.shrinkwrap.api.Archive;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public interface JavaSourceContainer<T extends Archive<T>>
{
   T add(JavaType<?> type);
}
