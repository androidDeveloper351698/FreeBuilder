/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.inferred.freebuilder.processor;

import static com.google.common.truth.Truth.assertThat;

import static org.inferred.freebuilder.processor.GenericTypeElementImpl.newTopLevelGenericType;
import static org.inferred.freebuilder.processor.util.ClassTypeImpl.newTopLevelClass;
import static org.inferred.freebuilder.processor.util.PrimitiveTypeImpl.INT;
import static org.inferred.freebuilder.processor.util.SourceLevel.JAVA_6;
import static org.inferred.freebuilder.processor.util.SourceLevel.JAVA_7;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import org.inferred.freebuilder.processor.GenericTypeElementImpl.GenericTypeMirrorImpl;
import org.inferred.freebuilder.processor.Metadata.Property;
import org.inferred.freebuilder.processor.util.ClassTypeImpl;
import org.inferred.freebuilder.processor.util.QualifiedName;
import org.inferred.freebuilder.processor.util.SourceLevel;
import org.inferred.freebuilder.processor.util.SourceStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.lang.model.type.TypeMirror;

@RunWith(JUnit4.class)
public class MapSourceTest {

  @Test
  public void test_j6() {
    Metadata metadata = createMetadata();

    assertThat(generateSource(metadata, JAVA_6)).isEqualTo(Joiner.on('\n').join(
        "/**",
        " * Auto-generated superclass of {@link Person.Builder},",
        " * derived from the API of {@link Person}.",
        " */",
        "@Generated(\"org.inferred.freebuilder.processor.CodeGenerator\")",
        "abstract class Person_Builder {",
        "",
        "  private final LinkedHashMap<Integer, String> name = "
            + "new LinkedHashMap<Integer, String>();",
        "",
        "  /**",
        "   * Associates {@code key} with {@code value} in the map to be returned from",
        "   * {@link Person#getName()}.",
        "   * Duplicate keys are not allowed.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code value} is null",
        "   * @throws IllegalArgumentException if {@code key} is already present",
        "   */",
        "  public Person.Builder putName(int key, String value) {",
        "    Preconditions.checkNotNull(value);",
        "    Preconditions.checkArgument(!name.containsKey(key), "
            + "\"Key already present in name: %s\", key);",
        "    name.put(key, value);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Associates all of {@code map}'s keys and values in the map to be returned",
        "   * from {@link Person#getName()}.",
        "   * Duplicate keys are not allowed.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code map} is null or contains a",
        "   *     null key or value",
        "   * @throws IllegalArgumentException if any key is already present",
        "   */",
        "  public Person.Builder putAllName(Map<? extends Integer, ? extends String> map) {",
        "    for (Map.Entry<? extends Integer, ? extends String> entry : map.entrySet()) {",
        "      putName(entry.getKey(), entry.getValue());",
        "    }",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes the mapping for {@code key} from the map to be returned from",
        "   * {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws IllegalArgumentException if {@code key} is not present",
        "   */",
        "  public Person.Builder removeName(int key) {",
        "    Preconditions.checkArgument(name.containsKey(key), "
            + "\"Key not present in name: %s\", key);",
        "    name.remove(key);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes all of the mappings from the map to be returned from",
        "   * {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder clearName() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns an unmodifiable view of the map that will be returned by",
        "   * {@link Person#getName()}.",
        "   * Changes to this builder will be reflected in the view.",
        "   */",
        "  public Map<Integer, String> getName() {",
        "    return Collections.unmodifiableMap(name);",
        "  }",
        "",
        "  /**",
        "   * Sets all property values using the given {@code Person} as a template.",
        "   */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    putAllName(value.getName());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    putAllName(((Person_Builder) template).name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Resets the state of this builder.",
        "   */",
        "  public Person.Builder clear() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created {@link Person} based on the contents of the {@code Builder}.",
        "   */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person}",
        "   * based on the contents of the {@code Builder}.",
        "   * State checking will not be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      if (!name.equals(other.name)) {",
        "        return false;",
        "      }",
        "      return true;",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Arrays.hashCode(new Object[] {name});",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      if (!name.equals(other.name)) {",
        "        return false;",
        "      }",
        "      return true;",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Arrays.hashCode(new Object[] {name});",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "}\n"));
  }

  @Test
  public void test_j7() {
    Metadata metadata = createMetadata();

    assertThat(generateSource(metadata, JAVA_7)).isEqualTo(Joiner.on('\n').join(
        "/**",
        " * Auto-generated superclass of {@link Person.Builder},",
        " * derived from the API of {@link Person}.",
        " */",
        "@Generated(\"org.inferred.freebuilder.processor.CodeGenerator\")",
        "abstract class Person_Builder {",
        "",
        "  private final LinkedHashMap<Integer, String> name = new LinkedHashMap<>();",
        "",
        "  /**",
        "   * Associates {@code key} with {@code value} in the map to be returned from",
        "   * {@link Person#getName()}.",
        "   * Duplicate keys are not allowed.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code value} is null",
        "   * @throws IllegalArgumentException if {@code key} is already present",
        "   */",
        "  public Person.Builder putName(int key, String value) {",
        "    Preconditions.checkNotNull(value);",
        "    Preconditions.checkArgument(!name.containsKey(key), "
            + "\"Key already present in name: %s\", key);",
        "    name.put(key, value);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Associates all of {@code map}'s keys and values in the map to be returned",
        "   * from {@link Person#getName()}.",
        "   * Duplicate keys are not allowed.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws NullPointerException if {@code map} is null or contains a",
        "   *     null key or value",
        "   * @throws IllegalArgumentException if any key is already present",
        "   */",
        "  public Person.Builder putAllName(Map<? extends Integer, ? extends String> map) {",
        "    for (Map.Entry<? extends Integer, ? extends String> entry : map.entrySet()) {",
        "      putName(entry.getKey(), entry.getValue());",
        "    }",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes the mapping for {@code key} from the map to be returned from",
        "   * {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   * @throws IllegalArgumentException if {@code key} is not present",
        "   */",
        "  public Person.Builder removeName(int key) {",
        "    Preconditions.checkArgument(name.containsKey(key), "
            + "\"Key not present in name: %s\", key);",
        "    name.remove(key);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Removes all of the mappings from the map to be returned from",
        "   * {@link Person#getName()}.",
        "   *",
        "   * @return this {@code Builder} object",
        "   */",
        "  public Person.Builder clearName() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns an unmodifiable view of the map that will be returned by",
        "   * {@link Person#getName()}.",
        "   * Changes to this builder will be reflected in the view.",
        "   */",
        "  public Map<Integer, String> getName() {",
        "    return Collections.unmodifiableMap(name);",
        "  }",
        "",
        "  /**",
        "   * Sets all property values using the given {@code Person} as a template.",
        "   */",
        "  public Person.Builder mergeFrom(Person value) {",
        "    putAllName(value.getName());",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Copies values from the given {@code Builder}.",
        "   */",
        "  public Person.Builder mergeFrom(Person.Builder template) {",
        "    putAllName(((Person_Builder) template).name);",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Resets the state of this builder.",
        "   */",
        "  public Person.Builder clear() {",
        "    name.clear();",
        "    return (Person.Builder) this;",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created {@link Person} based on the contents of the {@code Builder}.",
        "   */",
        "  public Person build() {",
        "    return new Person_Builder.Value(this);",
        "  }",
        "",
        "  /**",
        "   * Returns a newly-created partial {@link Person}",
        "   * based on the contents of the {@code Builder}.",
        "   * State checking will not be performed.",
        "   *",
        "   * <p>Partials should only ever be used in tests.",
        "   */",
        "  @VisibleForTesting()",
        "  public Person buildPartial() {",
        "    return new Person_Builder.Partial(this);",
        "  }",
        "",
        "  private static final class Value extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    private Value(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Value)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Value other = (Person_Builder.Value) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "",
        "  private static final class Partial extends Person {",
        "    private final Map<Integer, String> name;",
        "",
        "    Partial(Person_Builder builder) {",
        "      this.name = ImmutableMap.copyOf(builder.name);",
        "    }",
        "",
        "    @Override",
        "    public Map<Integer, String> getName() {",
        "      return name;",
        "    }",
        "",
        "    @Override",
        "    public boolean equals(Object obj) {",
        "      if (!(obj instanceof Person_Builder.Partial)) {",
        "        return false;",
        "      }",
        "      Person_Builder.Partial other = (Person_Builder.Partial) obj;",
        "      return Objects.equals(name, other.name);",
        "    }",
        "",
        "    @Override",
        "    public int hashCode() {",
        "      return Objects.hash(name);",
        "    }",
        "",
        "    @Override",
        "    public String toString() {",
        "      return \"partial Person{name=\" + name + \"}\";",
        "    }",
        "  }",
        "}\n"));
  }

  private static String generateSource(Metadata metadata, SourceLevel sourceLevel) {
    SourceStringBuilder sourceBuilder = SourceStringBuilder.simple(sourceLevel);
    new CodeGenerator().writeBuilderSource(sourceBuilder, metadata);
    try {
      return new Formatter().formatSource(sourceBuilder.toString());
    } catch (FormatterException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns a {@link Metadata} instance for a FreeBuilder type with a single property, name, of
   * type {@code Map<Integer, String>}.
   */
  private static Metadata createMetadata() {
    GenericTypeElementImpl map = newTopLevelGenericType("java.util.Map");
    ClassTypeImpl integer = newTopLevelClass("java.lang.Integer");
    ClassTypeImpl string = newTopLevelClass("java.lang.String");
    GenericTypeMirrorImpl mapIntString = map.newMirror(integer, string);
    QualifiedName person = QualifiedName.of("com.example", "Person");
    QualifiedName generatedBuilder = QualifiedName.of("com.example", "Person_Builder");
    Property.Builder name = new Property.Builder()
        .setAllCapsName("NAME")
        .setBoxedType(mapIntString)
        .setCapitalizedName("Name")
        .setFullyCheckedCast(true)
        .setGetterName("getName")
        .setName("name")
        .setType(mapIntString);
    Metadata metadata = new Metadata.Builder()
        .setBuilder(person.nestedType("Builder").withParameters())
        .setBuilderFactory(BuilderFactory.NO_ARGS_CONSTRUCTOR)
        .setBuilderSerializable(false)
        .setGeneratedBuilder(generatedBuilder.withParameters())
        .setGwtCompatible(false)
        .setGwtSerializable(false)
        .setInterfaceType(false)
        .setPartialType(generatedBuilder.nestedType("Partial").withParameters())
        .addProperties(name
            .setCodeGenerator(new MapPropertyFactory.CodeGenerator(
                name.build(),
                integer, Optional.<TypeMirror>of(INT),
                string, Optional.<TypeMirror>absent()))
            .build())
        .setPropertyEnum(generatedBuilder.nestedType("Property").withParameters())
        .setType(person.withParameters())
        .setValueType(generatedBuilder.nestedType("Value").withParameters())
        .build();
    return metadata;
  }

}
