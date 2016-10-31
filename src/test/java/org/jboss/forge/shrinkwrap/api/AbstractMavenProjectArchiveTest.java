/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.shrinkwrap.api;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.assertj.core.api.Assertions;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.shrinkwrap.container.JavaSourceContainer;
import org.jboss.forge.shrinkwrap.container.MavenProjectContainer;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
abstract class AbstractMavenProjectArchiveTest
{
   protected abstract Archive<?> getArchive();

   protected abstract String getJavaPath();

   @Test
   public void testArchiveIsCreated()
   {
      Archive<?> archive = getArchive();
      Assert.assertThat(archive, notNullValue());
   }

   @Test
   public void testAddJavaSourceInMavenProjectArchive()
   {
      Archive<?> archive = getArchive();
      ((JavaSourceContainer<?>) archive)
               .add(Roaster.create(JavaClassSource.class).setName("Foo").setPackage("com.example"));
      Node node = archive.get(getJavaPath() + "/com/example/Foo.java");
      Assert.assertThat(node, notNullValue());
   }

   @Test
   public void testMavenModel()
   {
      Model model = new Model();
      model.setGroupId("org.example");
      model.setArtifactId("foo");
      model.setVersion("1.0.0-SNAPSHOT");
      Dependency dep = new Dependency();
      dep.setGroupId("depGroup");
      dep.setGroupId("depArtifact");
      dep.setVersion("1");
      model.getDependencies().add(dep);

      Archive<?> archive = getArchive();
      ((MavenProjectContainer<?>) archive).setPom(model);

      Node node = archive.get("pom.xml");
      Assert.assertThat(node, notNullValue());
   }

   @Test
   public void testExportToZip() throws IOException
   {
      Model model = new Model();
      model.setGroupId("org.example");
      model.setArtifactId("foo");
      model.setVersion("1.0.0-SNAPSHOT");
      Dependency dep = new Dependency();
      dep.setGroupId("depGroup");
      dep.setGroupId("depArtifact");
      dep.setVersion("1");
      model.getDependencies().add(dep);

      Archive<?> archive = getArchive();
      ((MavenProjectContainer<?>) archive).setPom(model);

      ((JavaSourceContainer<?>) archive)
               .add(Roaster.create(JavaClassSource.class).setName("Foo").setPackage("com.example"));

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      archive.as(ZipExporter.class).exportTo(baos);
      List<String> lines = new ArrayList<>();
      try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray())))
      {
         ZipEntry entry;
         while ((entry = zis.getNextEntry()) != null)
         {
            lines.add(entry.getName());
         }
      }
      Assertions.assertThat(lines).contains("pom.xml", getJavaPath() + "/com/example/Foo.java");
   }

}
