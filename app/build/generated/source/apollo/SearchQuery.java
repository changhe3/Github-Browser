import com.apollographql.apollo.api.FragmentResponseFieldMapper;
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
import fragment.PageInfoQuery;
import fragment.RepoItemQuery;
import fragment.UserListItemQuery;
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
import type.SearchType;

@Generated("Apollo GraphQL")
public final class SearchQuery implements Query<SearchQuery.Data, SearchQuery.Data, SearchQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query Search($str: String!, $first: Int!, $after: String, $type: SearchType!) {\n"
      + "  search(query: $str, first: $first, after: $after, type: $type) {\n"
      + "    __typename\n"
      + "    pageInfo {\n"
      + "      __typename\n"
      + "      ...PageInfoQuery\n"
      + "    }\n"
      + "    nodes {\n"
      + "      __typename\n"
      + "      ... on Repository {\n"
      + "        ...RepoItemQuery\n"
      + "      }\n"
      + "      ... on User {\n"
      + "        ...UserListItemQuery\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION + "\n"
   + RepoItemQuery.FRAGMENT_DEFINITION + "\n"
   + UserListItemQuery.FRAGMENT_DEFINITION + "\n"
   + PageInfoQuery.FRAGMENT_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "Search";
    }
  };

  private final SearchQuery.Variables variables;

  public SearchQuery(@Nonnull String str, int first, @Nullable String after,
      @Nonnull SearchType type) {
    Utils.checkNotNull(str, "str == null");
    Utils.checkNotNull(type, "type == null");
    variables = new SearchQuery.Variables(str, first, after, type);
  }

  @Override
  public String operationId() {
    return "953d8e591f8e49eaf8d01965f056b41303a0f9d9aea2f304ba76eea3e6fd7531";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public SearchQuery.Data wrapData(SearchQuery.Data data) {
    return data;
  }

  @Override
  public SearchQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<SearchQuery.Data> responseFieldMapper() {
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
    private @Nonnull String str;

    private int first;

    private @Nullable String after;

    private @Nonnull SearchType type;

    Builder() {
    }

    public Builder str(@Nonnull String str) {
      this.str = str;
      return this;
    }

    public Builder first(int first) {
      this.first = first;
      return this;
    }

    public Builder after(@Nullable String after) {
      this.after = after;
      return this;
    }

    public Builder type(@Nonnull SearchType type) {
      this.type = type;
      return this;
    }

    public SearchQuery build() {
      Utils.checkNotNull(str, "str == null");
      Utils.checkNotNull(type, "type == null");
      return new SearchQuery(str, first, after, type);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String str;

    private final int first;

    private final @Nullable String after;

    private final @Nonnull SearchType type;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String str, int first, @Nullable String after, @Nonnull SearchType type) {
      this.str = str;
      this.first = first;
      this.after = after;
      this.type = type;
      this.valueMap.put("str", str);
      this.valueMap.put("first", first);
      this.valueMap.put("after", after);
      this.valueMap.put("type", type);
    }

    public @Nonnull String str() {
      return str;
    }

    public int first() {
      return first;
    }

    public @Nullable String after() {
      return after;
    }

    public @Nonnull SearchType type() {
      return type;
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
          writer.writeString("str", str);
          writer.writeInt("first", first);
          writer.writeString("after", after);
          writer.writeString("type", type.name());
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("search", "search", new UnmodifiableMapBuilder<String, Object>(4)
        .put("query", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "str")
        .build())
        .put("after", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "after")
        .build())
        .put("type", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "type")
        .build())
        .put("first", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "first")
        .build())
      .build(), false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull Search search;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nonnull Search search) {
      this.search = Utils.checkNotNull(search, "search == null");
    }

    /**
     * Perform a search across resources.
     */
    public @Nonnull Search search() {
      return this.search;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], search.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "search=" + search
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
        return this.search.equals(that.search);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= search.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Search.Mapper searchFieldMapper = new Search.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final Search search = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<Search>() {
          @Override
          public Search read(ResponseReader reader) {
            return searchFieldMapper.map(reader);
          }
        });
        return new Data(search);
      }
    }
  }

  public static class Search {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("pageInfo", "pageInfo", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("nodes", "nodes", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nonnull PageInfo pageInfo;

    final @Nullable List<Node> nodes;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Search(@Nonnull String __typename, @Nonnull PageInfo pageInfo,
        @Nullable List<Node> nodes) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.pageInfo = Utils.checkNotNull(pageInfo, "pageInfo == null");
      this.nodes = nodes;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * Information to aid in pagination.
     */
    public @Nonnull PageInfo pageInfo() {
      return this.pageInfo;
    }

    /**
     * A list of nodes.
     */
    public @Nullable List<Node> nodes() {
      return this.nodes;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeObject($responseFields[1], pageInfo.marshaller());
          writer.writeList($responseFields[2], nodes, new ResponseWriter.ListWriter() {
            @Override
            public void write(Object value, ResponseWriter.ListItemWriter listItemWriter) {
              listItemWriter.writeObject(((Node) value).marshaller());
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Search{"
          + "__typename=" + __typename + ", "
          + "pageInfo=" + pageInfo + ", "
          + "nodes=" + nodes
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Search) {
        Search that = (Search) o;
        return this.__typename.equals(that.__typename)
         && this.pageInfo.equals(that.pageInfo)
         && ((this.nodes == null) ? (that.nodes == null) : this.nodes.equals(that.nodes));
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
        h ^= pageInfo.hashCode();
        h *= 1000003;
        h ^= (nodes == null) ? 0 : nodes.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Search> {
      final PageInfo.Mapper pageInfoFieldMapper = new PageInfo.Mapper();

      final Node.Mapper nodeFieldMapper = new Node.Mapper();

      @Override
      public Search map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final PageInfo pageInfo = reader.readObject($responseFields[1], new ResponseReader.ObjectReader<PageInfo>() {
          @Override
          public PageInfo read(ResponseReader reader) {
            return pageInfoFieldMapper.map(reader);
          }
        });
        final List<Node> nodes = reader.readList($responseFields[2], new ResponseReader.ListReader<Node>() {
          @Override
          public Node read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Node>() {
              @Override
              public Node read(ResponseReader reader) {
                return nodeFieldMapper.map(reader);
              }
            });
          }
        });
        return new Search(__typename, pageInfo, nodes);
      }
    }
  }

  public static class PageInfo {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forFragment("__typename", "__typename", Arrays.asList("PageInfo"))
    };

    final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public PageInfo(@Nonnull String __typename, @Nonnull Fragments fragments) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.fragments = Utils.checkNotNull(fragments, "fragments == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nonnull Fragments fragments() {
      return this.fragments;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          fragments.marshaller().marshal(writer);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "PageInfo{"
          + "__typename=" + __typename + ", "
          + "fragments=" + fragments
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof PageInfo) {
        PageInfo that = (PageInfo) o;
        return this.__typename.equals(that.__typename)
         && this.fragments.equals(that.fragments);
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
        h ^= fragments.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static class Fragments {
      final @Nonnull PageInfoQuery pageInfoQuery;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nonnull PageInfoQuery pageInfoQuery) {
        this.pageInfoQuery = Utils.checkNotNull(pageInfoQuery, "pageInfoQuery == null");
      }

      public @Nonnull PageInfoQuery pageInfoQuery() {
        return this.pageInfoQuery;
      }

      public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() {
          @Override
          public void marshal(ResponseWriter writer) {
            final PageInfoQuery $pageInfoQuery = pageInfoQuery;
            if ($pageInfoQuery != null) {
              $pageInfoQuery.marshaller().marshal(writer);
            }
          }
        };
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "pageInfoQuery=" + pageInfoQuery
            + "}";
        }
        return $toString;
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Fragments) {
          Fragments that = (Fragments) o;
          return this.pageInfoQuery.equals(that.pageInfoQuery);
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= pageInfoQuery.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final PageInfoQuery.Mapper pageInfoQueryFieldMapper = new PageInfoQuery.Mapper();

        @Override
        public @Nonnull Fragments map(ResponseReader reader, @Nonnull String conditionalType) {
          PageInfoQuery pageInfoQuery = null;
          if (PageInfoQuery.POSSIBLE_TYPES.contains(conditionalType)) {
            pageInfoQuery = pageInfoQueryFieldMapper.map(reader);
          }
          return new Fragments(Utils.checkNotNull(pageInfoQuery, "pageInfoQuery == null"));
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<PageInfo> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      @Override
      public PageInfo map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Fragments fragments = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        });
        return new PageInfo(__typename, fragments);
      }
    }
  }

  public static class Node {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInlineFragment("__typename", "__typename", Arrays.asList("Repository")),
      ResponseField.forInlineFragment("__typename", "__typename", Arrays.asList("User"))
    };

    final @Nonnull String __typename;

    final @Nullable AsRepository asRepository;

    final @Nullable AsUser asUser;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node(@Nonnull String __typename, @Nullable AsRepository asRepository,
        @Nullable AsUser asUser) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.asRepository = asRepository;
      this.asUser = asUser;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nullable AsRepository asRepository() {
      return this.asRepository;
    }

    public @Nullable AsUser asUser() {
      return this.asUser;
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
          final AsUser $asUser = asUser;
          if ($asUser != null) {
            $asUser.marshaller().marshal(writer);
          }
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Node{"
          + "__typename=" + __typename + ", "
          + "asRepository=" + asRepository + ", "
          + "asUser=" + asUser
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
         && ((this.asRepository == null) ? (that.asRepository == null) : this.asRepository.equals(that.asRepository))
         && ((this.asUser == null) ? (that.asUser == null) : this.asUser.equals(that.asUser));
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
        h *= 1000003;
        h ^= (asUser == null) ? 0 : asUser.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Node> {
      final AsRepository.Mapper asRepositoryFieldMapper = new AsRepository.Mapper();

      final AsUser.Mapper asUserFieldMapper = new AsUser.Mapper();

      @Override
      public Node map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final AsRepository asRepository = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<AsRepository>() {
          @Override
          public AsRepository read(String conditionalType, ResponseReader reader) {
            return asRepositoryFieldMapper.map(reader);
          }
        });
        final AsUser asUser = reader.readConditional($responseFields[2], new ResponseReader.ConditionalTypeReader<AsUser>() {
          @Override
          public AsUser read(String conditionalType, ResponseReader reader) {
            return asUserFieldMapper.map(reader);
          }
        });
        return new Node(__typename, asRepository, asUser);
      }
    }
  }

  public static class AsRepository {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forFragment("__typename", "__typename", Arrays.asList("Repository"))
    };

    final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public AsRepository(@Nonnull String __typename, @Nonnull Fragments fragments) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.fragments = Utils.checkNotNull(fragments, "fragments == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nonnull Fragments fragments() {
      return this.fragments;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          fragments.marshaller().marshal(writer);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsRepository{"
          + "__typename=" + __typename + ", "
          + "fragments=" + fragments
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
         && this.fragments.equals(that.fragments);
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
        h ^= fragments.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static class Fragments {
      final @Nullable RepoItemQuery repoItemQuery;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nullable RepoItemQuery repoItemQuery) {
        this.repoItemQuery = repoItemQuery;
      }

      public @Nullable RepoItemQuery repoItemQuery() {
        return this.repoItemQuery;
      }

      public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() {
          @Override
          public void marshal(ResponseWriter writer) {
            final RepoItemQuery $repoItemQuery = repoItemQuery;
            if ($repoItemQuery != null) {
              $repoItemQuery.marshaller().marshal(writer);
            }
          }
        };
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "repoItemQuery=" + repoItemQuery
            + "}";
        }
        return $toString;
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Fragments) {
          Fragments that = (Fragments) o;
          return ((this.repoItemQuery == null) ? (that.repoItemQuery == null) : this.repoItemQuery.equals(that.repoItemQuery));
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= (repoItemQuery == null) ? 0 : repoItemQuery.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final RepoItemQuery.Mapper repoItemQueryFieldMapper = new RepoItemQuery.Mapper();

        @Override
        public @Nonnull Fragments map(ResponseReader reader, @Nonnull String conditionalType) {
          RepoItemQuery repoItemQuery = null;
          if (RepoItemQuery.POSSIBLE_TYPES.contains(conditionalType)) {
            repoItemQuery = repoItemQueryFieldMapper.map(reader);
          }
          return new Fragments(repoItemQuery);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<AsRepository> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      @Override
      public AsRepository map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Fragments fragments = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        });
        return new AsRepository(__typename, fragments);
      }
    }
  }

  public static class AsUser {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forFragment("__typename", "__typename", Arrays.asList("User"))
    };

    final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public AsUser(@Nonnull String __typename, @Nonnull Fragments fragments) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.fragments = Utils.checkNotNull(fragments, "fragments == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    public @Nonnull Fragments fragments() {
      return this.fragments;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          fragments.marshaller().marshal(writer);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsUser{"
          + "__typename=" + __typename + ", "
          + "fragments=" + fragments
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsUser) {
        AsUser that = (AsUser) o;
        return this.__typename.equals(that.__typename)
         && this.fragments.equals(that.fragments);
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
        h ^= fragments.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static class Fragments {
      final @Nullable UserListItemQuery userListItemQuery;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nullable UserListItemQuery userListItemQuery) {
        this.userListItemQuery = userListItemQuery;
      }

      public @Nullable UserListItemQuery userListItemQuery() {
        return this.userListItemQuery;
      }

      public ResponseFieldMarshaller marshaller() {
        return new ResponseFieldMarshaller() {
          @Override
          public void marshal(ResponseWriter writer) {
            final UserListItemQuery $userListItemQuery = userListItemQuery;
            if ($userListItemQuery != null) {
              $userListItemQuery.marshaller().marshal(writer);
            }
          }
        };
      }

      @Override
      public String toString() {
        if ($toString == null) {
          $toString = "Fragments{"
            + "userListItemQuery=" + userListItemQuery
            + "}";
        }
        return $toString;
      }

      @Override
      public boolean equals(Object o) {
        if (o == this) {
          return true;
        }
        if (o instanceof Fragments) {
          Fragments that = (Fragments) o;
          return ((this.userListItemQuery == null) ? (that.userListItemQuery == null) : this.userListItemQuery.equals(that.userListItemQuery));
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= (userListItemQuery == null) ? 0 : userListItemQuery.hashCode();
          $hashCode = h;
          $hashCodeMemoized = true;
        }
        return $hashCode;
      }

      public static final class Mapper implements FragmentResponseFieldMapper<Fragments> {
        final UserListItemQuery.Mapper userListItemQueryFieldMapper = new UserListItemQuery.Mapper();

        @Override
        public @Nonnull Fragments map(ResponseReader reader, @Nonnull String conditionalType) {
          UserListItemQuery userListItemQuery = null;
          if (UserListItemQuery.POSSIBLE_TYPES.contains(conditionalType)) {
            userListItemQuery = userListItemQueryFieldMapper.map(reader);
          }
          return new Fragments(userListItemQuery);
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<AsUser> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      @Override
      public AsUser map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Fragments fragments = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        });
        return new AsUser(__typename, fragments);
      }
    }
  }
}
