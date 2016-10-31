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
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.impl.base.container.ContainerBase;
import org.jboss.shrinkwrap.impl.base.path.BasicPath;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public abstract class AbstractMavenProjectArchive<T extends Archive<T>> extends ContainerBase<T>
         implements MavenProjectContainer<T>, JavaSourceContainer<T>
{
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
}