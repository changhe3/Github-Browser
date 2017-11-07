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
import type.RepositoryOrder;

@Generated("Apollo GraphQL")
public final class ReposQuery implements Query<ReposQuery.Data, ReposQuery.Data, ReposQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query Repos($login: String!, $first: Int!, $after: String, $order: RepositoryOrder) {\n"
      + "  user(login: $login) {\n"
      + "    __typename\n"
      + "    repositories(first: $first, after: $after, orderBy: $order) {\n"
      + "      __typename\n"
      + "      totalCount\n"
      + "      pageInfo {\n"
      + "        __typename\n"
      + "        ...PageInfoQuery\n"
      + "      }\n"
      + "      nodes {\n"
      + "        __typename\n"
      + "        ...RepoItemQuery\n"
      + "      }\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION + "\n"
   + RepoItemQuery.FRAGMENT_DEFINITION + "\n"
   + PageInfoQuery.FRAGMENT_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "Repos";
    }
  };

  private final ReposQuery.Variables variables;

  public ReposQuery(@Nonnull String login, int first, @Nullable String after,
      @Nullable RepositoryOrder order) {
    Utils.checkNotNull(login, "login == null");
    variables = new ReposQuery.Variables(login, first, after, order);
  }

  @Override
  public String operationId() {
    return "e66b87bb6d8a0d47db3bed66548d9ba71d1745aeef4da55d14a2c28e0018f85a";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public ReposQuery.Data wrapData(ReposQuery.Data data) {
    return data;
  }

  @Override
  public ReposQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<ReposQuery.Data> responseFieldMapper() {
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
    private @Nonnull String login;

    private int first;

    private @Nullable String after;

    private @Nullable RepositoryOrder order;

    Builder() {
    }

    public Builder login(@Nonnull String login) {
      this.login = login;
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

    public Builder order(@Nullable RepositoryOrder order) {
      this.order = order;
      return this;
    }

    public ReposQuery build() {
      Utils.checkNotNull(login, "login == null");
      return new ReposQuery(login, first, after, order);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String login;

    private final int first;

    private final @Nullable String after;

    private final @Nullable RepositoryOrder order;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String login, int first, @Nullable String after,
        @Nullable RepositoryOrder order) {
      this.login = login;
      this.first = first;
      this.after = after;
      this.order = order;
      this.valueMap.put("login", login);
      this.valueMap.put("first", first);
      this.valueMap.put("after", after);
      this.valueMap.put("order", order);
    }

    public @Nonnull String login() {
      return login;
    }

    public int first() {
      return first;
    }

    public @Nullable String after() {
      return after;
    }

    public @Nullable RepositoryOrder order() {
      return order;
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
          writer.writeString("login", login);
          writer.writeInt("first", first);
          writer.writeString("after", after);
          writer.writeObject("order", order != null ? order.marshaller() : null);
        }
      };
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forObject("user", "user", new UnmodifiableMapBuilder<String, Object>(1)
        .put("login", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "login")
        .build())
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nullable User user;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Data(@Nullable User user) {
      this.user = user;
    }

    /**
     * Lookup a user by login.
     */
    public @Nullable User user() {
      return this.user;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeObject($responseFields[0], user != null ? user.marshaller() : null);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "user=" + user
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
        return ((this.user == null) ? (that.user == null) : this.user.equals(that.user));
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= (user == null) ? 0 : user.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final User.Mapper userFieldMapper = new User.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final User user = reader.readObject($responseFields[0], new ResponseReader.ObjectReader<User>() {
          @Override
          public User read(ResponseReader reader) {
            return userFieldMapper.map(reader);
          }
        });
        return new Data(user);
      }
    }
  }

  public static class User {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("repositories", "repositories", new UnmodifiableMapBuilder<String, Object>(3)
        .put("orderBy", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "order")
        .build())
        .put("after", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "after")
        .build())
        .put("first", new UnmodifiableMapBuilder<String, Object>(2)
          .put("kind", "Variable")
          .put("variableName", "first")
        .build())
      .build(), false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nonnull Repositories repositories;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public User(@Nonnull String __typename, @Nonnull Repositories repositories) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.repositories = Utils.checkNotNull(repositories, "repositories == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * A list of repositories that the user owns.
     */
    public @Nonnull Repositories repositories() {
      return this.repositories;
    }

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeObject($responseFields[1], repositories.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "User{"
          + "__typename=" + __typename + ", "
          + "repositories=" + repositories
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof User) {
        User that = (User) o;
        return this.__typename.equals(that.__typename)
         && this.repositories.equals(that.repositories);
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
        h ^= repositories.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<User> {
      final Repositories.Mapper repositoriesFieldMapper = new Repositories.Mapper();

      @Override
      public User map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Repositories repositories = reader.readObject($responseFields[1], new ResponseReader.ObjectReader<Repositories>() {
          @Override
          public Repositories read(ResponseReader reader) {
            return repositoriesFieldMapper.map(reader);
          }
        });
        return new User(__typename, repositories);
      }
    }
  }

  public static class Repositories {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("totalCount", "totalCount", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("pageInfo", "pageInfo", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("nodes", "nodes", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int totalCount;

    final @Nonnull PageInfo pageInfo;

    final @Nullable List<Node> nodes;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Repositories(@Nonnull String __typename, int totalCount, @Nonnull PageInfo pageInfo,
        @Nullable List<Node> nodes) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.totalCount = totalCount;
      this.pageInfo = Utils.checkNotNull(pageInfo, "pageInfo == null");
      this.nodes = nodes;
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * Identifies the total count of items in the connection.
     */
    public int totalCount() {
      return this.totalCount;
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
          writer.writeInt($responseFields[1], totalCount);
          writer.writeObject($responseFields[2], pageInfo.marshaller());
          writer.writeList($responseFields[3], nodes, new ResponseWriter.ListWriter() {
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
        $toString = "Repositories{"
          + "__typename=" + __typename + ", "
          + "totalCount=" + totalCount + ", "
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
      if (o instanceof Repositories) {
        Repositories that = (Repositories) o;
        return this.__typename.equals(that.__typename)
         && this.totalCount == that.totalCount
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
        h ^= totalCount;
        h *= 1000003;
        h ^= pageInfo.hashCode();
        h *= 1000003;
        h ^= (nodes == null) ? 0 : nodes.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Repositories> {
      final PageInfo.Mapper pageInfoFieldMapper = new PageInfo.Mapper();

      final Node.Mapper nodeFieldMapper = new Node.Mapper();

      @Override
      public Repositories map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int totalCount = reader.readInt($responseFields[1]);
        final PageInfo pageInfo = reader.readObject($responseFields[2], new ResponseReader.ObjectReader<PageInfo>() {
          @Override
          public PageInfo read(ResponseReader reader) {
            return pageInfoFieldMapper.map(reader);
          }
        });
        final List<Node> nodes = reader.readList($responseFields[3], new ResponseReader.ListReader<Node>() {
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
        return new Repositories(__typename, totalCount, pageInfo, nodes);
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
      ResponseField.forFragment("__typename", "__typename", Arrays.asList("Repository"))
    };

    final @Nonnull String __typename;

    private final @Nonnull Fragments fragments;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Node(@Nonnull String __typename, @Nonnull Fragments fragments) {
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
        $toString = "Node{"
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
      if (o instanceof Node) {
        Node that = (Node) o;
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
      final @Nonnull RepoItemQuery repoItemQuery;

      private volatile String $toString;

      private volatile int $hashCode;

      private volatile boolean $hashCodeMemoized;

      public Fragments(@Nonnull RepoItemQuery repoItemQuery) {
        this.repoItemQuery = Utils.checkNotNull(repoItemQuery, "repoItemQuery == null");
      }

      public @Nonnull RepoItemQuery repoItemQuery() {
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
          return this.repoItemQuery.equals(that.repoItemQuery);
        }
        return false;
      }

      @Override
      public int hashCode() {
        if (!$hashCodeMemoized) {
          int h = 1;
          h *= 1000003;
          h ^= repoItemQuery.hashCode();
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
          return new Fragments(Utils.checkNotNull(repoItemQuery, "repoItemQuery == null"));
        }
      }
    }

    public static final class Mapper implements ResponseFieldMapper<Node> {
      final Fragments.Mapper fragmentsFieldMapper = new Fragments.Mapper();

      @Override
      public Node map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final Fragments fragments = reader.readConditional($responseFields[1], new ResponseReader.ConditionalTypeReader<Fragments>() {
          @Override
          public Fragments read(String conditionalType, ResponseReader reader) {
            return fragmentsFieldMapper.map(reader, conditionalType);
          }
        });
        return new Node(__typename, fragments);
      }
    }
  }
}
