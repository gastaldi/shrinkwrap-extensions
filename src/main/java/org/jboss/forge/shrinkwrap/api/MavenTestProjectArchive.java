/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.api;

import org.jboss.forge.shrinkwrap.container.JavaSourceContainer;
import org.jboss.forge.shrinkwrap.container.MavenProjectContainer;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.container.ManifestContainer;
import org.jboss.shrinkwrap.api.container.ResourceContainer;
import org.jboss.shrinkwrap.api.container.WebContainer;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public interface MavenTestProjectArchive extends Archive<MavenTestProjectArchive>,
         ResourceContainer<MavenTestProjectArchive>,
         ManifestContainer<MavenTestProjectArchive>,
         JavaSourceContainer<MavenTestProjectArchive>,
         MavenProjectContainer<MavenTestProjectArchive>,
         WebContainer<MavenTestProjectArchive>
{
}
