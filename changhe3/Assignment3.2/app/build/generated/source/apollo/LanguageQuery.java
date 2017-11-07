import com.apollographql.apollo.api.InputFieldMarshaller;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import java.io.IOException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Generated("Apollo GraphQL")
public final class LanguageQuery implements Query<LanguageQuery.Data, LanguageQuery.Data, LanguageQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query Language($repoId: ID!) {\n"
      + "  node(id: $repoId) {\n"
      + "    __typename\n"
      + "    ... on Repository {\n"
      + "      languages(first: 10, orderBy: {field: SIZE, direction: DESC}) {\n"
      + "        __typename\n"
      + "        totalSize\n"
      + "        edges {\n"
      + "          __typename\n"
      + "          size\n"
      + "          node {\n"
      + "            __typename\n"
      + "            color\n"
      + "            name\n"
      + "          }\n"
      + "        }\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "Language";
    }
  };

  private final LanguageQuery.Variables variables;

  public LanguageQuery(@Nonnull String repoId) {
    Utils.checkNotNull(repoId, "repoId == null");
    variables = new LanguageQuery.Variables(repoId);
  }

  @Override
  public String operationId() {
    return "65a6ed5a78c4591ca0979df7b6cb360a94190d6be09367893b418b679c5d7949";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public LanguageQuery.Data wrapData(LanguageQuery.Data data) {
    return data;
  }

  @Override
  public LanguageQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<LanguageQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  public static final class Builder {
    private @Nonnull String repoId;

    Builder() {
    }

    public Builder repoId(@Nonnull String repoId) {
      this.repoId = repoId;
      return this;
    }

    public LanguageQuery build() {
      Utils.checkNotNull(repoId, "repoId == null");
      return new LanguageQuery(repoId);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String repoId;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String repoId) {
      this.repoId = repoId;
      this.valueMap.put("repoId", repoId);
    }

    public @Nonnull String repoId() {
      return repoId;
    }

    @Override
    public Map<String, Object> valueMap() {
      return Collections.unmodifiableMap(valueMap);
    }

    @Override
    public InputFieldMarshaller marshaller() {
      return new InputFieldMarshaller() {
        @Override
        public void marshal(InputFieldWriter writer) throws IOException {
          writer.writeCustom("repoId", type.CustomType.ID, repoId);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("node", "node", new UnmodifiableMapBuilder<String, Object>(1)
        .put("id", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "repoId")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable Node node;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable Node node) {
      this.node = node;
    }

    /**
     * Fetches an object given its ID.
     */
    public @Nullable Node node() {
      return this.node;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], node != null ? node.marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "node=" + node
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return ((this.node == null) ? (that.node == null) : this.node.equals(that.node));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (node == null) ? 0 : node.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Node.Mapper nodeFieldMapper = new Node.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final Node node = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Node>() {
          @Override
          public Node read(ResponseReader reader) {
            return nodeFieldMapper.map(reader);
          }
        });
        return new Data(node);
      }
    }
  }

  public static class Node {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInlineFragment("__typename", "__typename", Arrays.asList("Repository"))
    };

    final @Nonnull String __typename;

    final @Nullable AsRepository asRepository;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node(@Nonnull String __typename, @Nullable AsRepository asRepository) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.asRepository = asRepository;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable AsRepository asRepository() {
      return this.asRepository;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          final AsRepository $asRepository = asRepository;
          if ($asRepository != null) {
            $asRepository.marshaller().marshal(writer);
          }
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node{"
          + "__typename=" + __typename + ", "
          + "asRepository=" + asRepository
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Node) {
        Node that = (Node) o;
        return this.__typename.equals(that.__typename)
         && ((this.asRepository == null) ? (that.asRepository == null) : this.asRepository.equals(that.asRepository));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= (asRepository == null) ? 0 : asRepository.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Node> {
      final AsRepository.Mapper asRepositoryFieldMapper = new AsRepository.Mapper();

      @Override
      public Node map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final AsRepository asRepository = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<AsRepository>() {
          @Override
          public AsRepository read(String conditionalType, ResponseReader reader) {
            return asRepositoryFieldMapper.map(reader);
          }
        });
        return new Node(__typename, asRepository);
      }
    }
  }

  public static class AsRepository {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("languages", "languages", new UnmodifiableMapBuilder<String, Object>(2)
        .put("orderBy", new UnmodifiableMapBuilder<String, Object>(2)
          .put("field", "SIZE")
          .put("direction", "DESC")
        .build())
        .put("first", "10.0")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable Languages languages;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public AsRepository(@Nonnull String __typename, @Nullable Languages languages) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.languages = languages;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * A list containing a breakdown of the language composition of the repository.
     */
    public @Nullable Languages languages() {
      return this.languages;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeObject($responseFields[1], languages != null ? languages.marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsRepository{"
          + "__typename=" + __typename + ", "
          + "languages=" + languages
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsRepository) {
        AsRepository that = (AsRepository) o;
        return this.__typename.equals(that.__typename)
         && ((this.languages == null) ? (that.languages == null) : this.languages.equals(that.languages));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= (languages == null) ? 0 : languages.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsRepository> {
      final Languages.Mapper languagesFieldMapper = new Languages.Mapper();

      @Override
      public AsRepository map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Languages languages = reader.readObject($responseFields[1], new ResponseReader.ObjectReader<Languages>() {
          @Override
          public Languages read(ResponseReader reader) {
            return languagesFieldMapper.map(reader);
          }
        });
        return new AsRepository(__typename, languages);
      }
    }
  }

  public static class Languages {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("totalSize", "totalSize", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("edges", "edges", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int totalSize;

    final @Nullable List<Edge> edges;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Languages(@Nonnull String __typename, int totalSize, @Nullable List<Edge> edges) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.totalSize = totalSize;
      this.edges = edges;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The total size in bytes of files written in that language.
     */
    public int totalSize() {
      return this.totalSize;
    }

    /**
     * A list of edges.
     */
    public @Nullable List<Edge> edges() {
      return this.edges;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], totalSize);
          writer.writeList($responseFields[2], edges, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((Edge) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Languages{"
          + "__typename=" + __typename + ", "
          + "totalSize=" + totalSize + ", "
          + "edges=" + edges
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Languages) {
        Languages that = (Languages) o;
        return this.__typename.equals(that.__typename)
         && this.totalSize == that.totalSize
         && ((this.edges == null) ? (that.edges == null) : this.edges.equals(that.edges));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= totalSize;
        h *= 1000003;
        h ^= (edges == null) ? 0 : edges.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Languages> {
      final Edge.Mapper edgeFieldMapper = new Edge.Mapper();

      @Override
      public Languages map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int totalSize = reader.readInt($responseFields[1]);
        final List<Edge> edges = reader.readList($responseFields[2], new ResponseReader.ListReader<Edge>() {
          @Override
          public Edge read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Edge>() {
              @Override
              public Edge read(ResponseReader reader) {
                return edgeFieldMapper.map(reader);
              }
            });
          }
        });
        return new Languages(__typename, totalSize, edges);
      }
    }
  }

  public static class Edge {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("size", "size", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("node", "node", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int size;

    final @Nonnull Node1 node;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Edge(@Nonnull String __typename, int size, @Nonnull Node1 node) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.size = size;
      this.node = Utils.checkNotNull(node, "node == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The number of bytes of code written in the language.
     */
    public int size() {
      return this.size;
    }

    public @Nonnull Node1 node() {
      return this.node;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], size);
          writer.writeObject($responseFields[2], node.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Edge{"
          + "__typename=" + __typename + ", "
          + "size=" + size + ", "
          + "node=" + node
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Edge) {
        Edge that = (Edge) o;
        return this.__typename.equals(that.__typename)
         && this.size == that.size
         && this.node.equals(that.node);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= size;
        h *= 1000003;
        h ^= node.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Edge> {
      final Node1.Mapper node1FieldMapper = new Node1.Mapper();

      @Override
      public Edge map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int size = reader.readInt($responseFields[1]);
        final Node1 node = reader.readObject($responseFields[2], new ResponseReader.ObjectReader<Node1>() {
          @Override
          public Node1 read(ResponseReader reader) {
            return node1FieldMapper.map(reader);
          }
        });
        return new Edge(__typename, size, node);
      }
    }
  }

  public static class Node1 {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("color", "color", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable String color;

    final @Nonnull String name;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node1(@Nonnull String __typename, @Nullable String color, @Nonnull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.color = color;
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The color defined for the current language.
     */
    public @Nullable String color() {
      return this.color;
    }

    /**
     * The name of the current language.
     */
    public @Nonnull String name() {
      return this.name;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], color);
          writer.writeString($responseFields[2], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node1{"
          + "__typename=" + __typename + ", "
          + "color=" + color + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Node1) {
        Node1 that = (Node1) o;
        return this.__typename.equals(that.__typename)
         && ((this.color == null) ? (that.color == null) : this.color.equals(that.color))
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= (color == null) ? 0 : color.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Node1> {
      @Override
      public Node1 map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String color = reader.readString($responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        return new Node1(__typename, color, name);
      }
    }
  }
}
