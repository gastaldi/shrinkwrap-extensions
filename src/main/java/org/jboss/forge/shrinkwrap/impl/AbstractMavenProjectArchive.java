/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.shrinkwrap.container.JavaSourceContainer;
import org.jboss.forge.shrinkwrap.container.MavenProjectContainer;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.impl.base.container.WebContainerBase;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public abstract class AbstractMavenProjectArchive<T extends Archive<T>> extends WebContainerBase<T>
         implements MavenProjectContainer<T>, JavaSourceContainer<T>
{
   /**
    * Path to the web inside of the Archive.
    */
   private static final ArchivePath PATH_WEB = ArchivePaths.create("src/main/webapp");

   /**
    * Path to the WEB-INF inside of the Archive.
    */
   private static final ArchivePath PATH_WEB_INF = ArchivePaths.create(PATH_WEB, "WEB-INF");

   /**
    * @param actualType
    * @param archive
    */
   public AbstractMavenProjectArchive(Class<T> actualType, Archive<?> archive)
   {
      super(actualType, archive);
   }

   @Override
   public T add(JavaType<?> type)
   {
      String path = type.getPackage().replaceAll("\\.", "/");
      BasicPath basicPath = new BasicPath(getClassesPath(), path + "/" + type.getName() + ".java");
      return add(new StringAsset(type.toString()), basicPath);
   }

   @Override
   public T setPom(Model model)
   {
      MavenXpp3Writer writer = new MavenXpp3Writer();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try
      {
         writer.write(baos, model);
      }
      catch (IOException e)
      {
         // Should never happen
      }
      return add(new StringAsset(baos.toString()), "pom.xml");
   }

   @Override
   public T setPom(Asset asset)
   {
      return add(asset, "pom.xml");
   }

   @Override
   protected ArchivePath getWebPath()
   {
      return PATH_WEB;
   }

   @Override
   protected ArchivePath getWebInfPath()
   {
      return PATH_WEB_INF;
   }

   @Override
   protected ArchivePath getServiceProvidersPath()
   {
      throw new UnsupportedOperationException("Service Providers not supported");
   }

}