/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.test.parser.java;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.jboss.forge.parser.JavaParser;
import org.jboss.forge.parser.java.source.AnnotationSource;
import org.jboss.forge.parser.java.source.FieldSource;
import org.jboss.forge.parser.java.source.JavaClassSource;
import org.jboss.forge.test.parser.java.common.AnnotationTest;
import org.jboss.forge.test.parser.java.common.MockEnumType;
import org.junit.Test;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class FieldAnnotationTest extends AnnotationTest<JavaClassSource, FieldSource<JavaClassSource>>
{
   @Override
   public void resetTests()
   {
      InputStream stream = FieldAnnotationTest.class
               .getResourceAsStream("/org/jboss/forge/grammar/java/MockAnnotatedField.java");
      FieldSource<JavaClassSource> field = JavaParser.parse(JavaClassSource.class, stream).getFields().get(0);
      setTarget(field);
   }

   @Test
   public void testParseEnumValueStaticImport() throws Exception
   {
      List<AnnotationSource<JavaClassSource>> annotations = getTarget().getAnnotations();
      AnnotationSource<JavaClassSource> annotation = annotations.get(annotations.size() - 2);
      MockEnumType enumValue = annotation.getEnumValue(MockEnumType.class);
      assertEquals(MockEnumType.FOO, enumValue);
   }
}
