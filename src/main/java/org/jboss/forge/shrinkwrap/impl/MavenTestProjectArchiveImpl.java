/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.impl;

import org.jboss.forge.shrinkwrap.api.MavenTestProjectArchive;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;

/**
 * 
 * {@link MavenTestProjectArchive} implementation
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public class MavenTestProjectArchiveImpl extends AbstractMavenProjectArchive<MavenTestProjectArchive>
         implements MavenTestProjectArchive
{
   /**
    * @param actualType
    * @param archive
    */
   public MavenTestProjectArchiveImpl(Archive<?> archive)
   {
      super(MavenTestProjectArchive.class, archive);
   }

   /**
    * Path to the manifests inside of the Archive.
    */
   private static final ArchivePath PATH_MANIFEST = new BasicPath("src/test/resources/META-INF");

   /**
    * Path to the resources inside of the Archive.
    */
   private static final ArchivePath PATH_JAVA = new BasicPath("src/test/java");

   /**
    * Path to the resources inside of the Archive.
    */
   private static final ArchivePath PATH_RESOURCE = new BasicPath("src/test/resources");

   @Override
   protected ArchivePath getManifestPath()
   {
      return PATH_MANIFEST;
   }

   @Override
   protected ArchivePath getResourcePath()
   {
      return PATH_RESOURCE;
   }

   @Override
   protected ArchivePath getClassesPath()
   {
      return PATH_JAVA;
   }

   @Override
   protected ArchivePath getLibraryPath()
   {
      throw new UnsupportedOperationException("MavenTestProjectArchive does not support Libraries");
   }

}
