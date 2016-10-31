/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.api;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public class MavenProjectArchiveTest extends AbstractMavenProjectArchiveTest
{
   @Override
   protected Archive<?> getArchive()
   {
      return ShrinkWrap.create(MavenProjectArchive.class);
   }

   @Override
   protected String getJavaPath()
   {
      return "src/main/java";
   }

   @Test
   public void asTestProject()
   {
      MavenTestProjectArchive archive = ShrinkWrap.create(MavenProjectArchive.class).as(MavenTestProjectArchive.class);
      Assert.assertThat(archive, notNullValue());
   }
}
