/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.api;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public class MavenTestProjectArchiveTest extends AbstractMavenProjectArchiveTest
{
   @Override
   protected Archive<?> getArchive()
   {
      return ShrinkWrap.create(MavenTestProjectArchive.class);
   }

   @Override
   protected String getJavaPath()
   {
      return "src/test/java";
   }

}
