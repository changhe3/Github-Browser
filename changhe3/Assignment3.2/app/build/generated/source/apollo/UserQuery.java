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
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import type.CustomType;

@Generated("Apollo GraphQL")
public final class UserQuery implements Query<UserQuery.Data, UserQuery.Data, UserQuery.Variables> {
  public static final String OPERATION_DEFINITION = "query User($login: String!) {\n"
      + "  user(login: $login) {\n"
      + "    __typename\n"
      + "    name\n"
      + "    avatarUrl\n"
      + "    login\n"
      + "    bio\n"
      + "    email\n"
      + "    followers {\n"
      + "      __typename\n"
      + "      totalCount\n"
      + "    }\n"
      + "    following {\n"
      + "      __typename\n"
      + "      totalCount\n"
      + "    }\n"
      + "    repositories {\n"
      + "      __typename\n"
      + "      totalCount\n"
      + "    }\n"
      + "  }\n"
      + "}";

  public static final String QUERY_DOCUMENT = OPERATION_DEFINITION;

  private static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "User";
    }
  };

  private final UserQuery.Variables variables;

  public UserQuery(@Nonnull String login) {
    Utils.checkNotNull(login, "login == null");
    variables = new UserQuery.Variables(login);
  }

  @Override
  public String operationId() {
    return "320a78575878bc120760da2d292a607baebb856c2bf33742db524b167c372361";
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public UserQuery.Data wrapData(UserQuery.Data data) {
    return data;
  }

  @Override
  public UserQuery.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<UserQuery.Data> responseFieldMapper() {
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

    Builder() {
    }

    public Builder login(@Nonnull String login) {
      this.login = login;
      return this;
    }

    public UserQuery build() {
      Utils.checkNotNull(login, "login == null");
      return new UserQuery(login);
    }
  }

  public static final class Variables extends Operation.Variables {
    private final @Nonnull String login;

    private final transient Map<String, Object> valueMap = new LinkedHashMap<>();

    Variables(@Nonnull String login) {
      this.login = login;
      this.valueMap.put("login", login);
    }

    public @Nonnull String login() {
      return login;
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
      ResponseField.forString("name", "name", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("avatarUrl", "avatarUrl", null, false, CustomType.URI, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("login", "login", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("bio", "bio", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("email", "email", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("followers", "followers", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("following", "following", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forObject("repositories", "repositories", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final @Nullable String name;

    final @Nonnull URL avatarUrl;

    final @Nonnull String login;

    final @Nullable String bio;

    final @Nonnull String email;

    final @Nonnull Followers followers;

    final @Nonnull Following following;

    final @Nonnull Repositories repositories;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public User(@Nonnull String __typename, @Nullable String name, @Nonnull URL avatarUrl,
        @Nonnull String login, @Nullable String bio, @Nonnull String email,
        @Nonnull Followers followers, @Nonnull Following following,
        @Nonnull Repositories repositories) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = name;
      this.avatarUrl = Utils.checkNotNull(avatarUrl, "avatarUrl == null");
      this.login = Utils.checkNotNull(login, "login == null");
      this.bio = bio;
      this.email = Utils.checkNotNull(email, "email == null");
      this.followers = Utils.checkNotNull(followers, "followers == null");
      this.following = Utils.checkNotNull(following, "following == null");
      this.repositories = Utils.checkNotNull(repositories, "repositories == null");
    }

    public @Nonnull String __typename() {
      return this.__typename;
    }

    /**
     * The user's public profile name.
     */
    public @Nullable String name() {
      return this.name;
    }

    /**
     * A URL pointing to the user's public avatar.
     */
    public @Nonnull URL avatarUrl() {
      return this.avatarUrl;
    }

    /**
     * The username used to login.
     */
    public @Nonnull String login() {
      return this.login;
    }

    /**
     * The user's public profile bio.
     */
    public @Nullable String bio() {
      return this.bio;
    }

    /**
     * The user's publicly visible profile email.
     */
    public @Nonnull String email() {
      return this.email;
    }

    /**
     * A list of users the given user is followed by.
     */
    public @Nonnull Followers followers() {
      return this.followers;
    }

    /**
     * A list of users the given user is following.
     */
    public @Nonnull Following following() {
      return this.following;
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
          writer.writeString($responseFields[1], name);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[2], avatarUrl);
          writer.writeString($responseFields[3], login);
          writer.writeString($responseFields[4], bio);
          writer.writeString($responseFields[5], email);
          writer.writeObject($responseFields[6], followers.marshaller());
          writer.writeObject($responseFields[7], following.marshaller());
          writer.writeObject($responseFields[8], repositories.marshaller());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "User{"
          + "__typename=" + __typename + ", "
          + "name=" + name + ", "
          + "avatarUrl=" + avatarUrl + ", "
          + "login=" + login + ", "
          + "bio=" + bio + ", "
          + "email=" + email + ", "
          + "followers=" + followers + ", "
          + "following=" + following + ", "
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
         && ((this.name == null) ? (that.name == null) : this.name.equals(that.name))
         && this.avatarUrl.equals(that.avatarUrl)
         && this.login.equals(that.login)
         && ((this.bio == null) ? (that.bio == null) : this.bio.equals(that.bio))
         && this.email.equals(that.email)
         && this.followers.equals(that.followers)
         && this.following.equals(that.following)
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
        h ^= (name == null) ? 0 : name.hashCode();
        h *= 1000003;
        h ^= avatarUrl.hashCode();
        h *= 1000003;
        h ^= login.hashCode();
        h *= 1000003;
        h ^= (bio == null) ? 0 : bio.hashCode();
        h *= 1000003;
        h ^= email.hashCode();
        h *= 1000003;
        h ^= followers.hashCode();
        h *= 1000003;
        h ^= following.hashCode();
        h *= 1000003;
        h ^= repositories.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<User> {
      final Followers.Mapper followersFieldMapper = new Followers.Mapper();

      final Following.Mapper followingFieldMapper = new Following.Mapper();

      final Repositories.Mapper repositoriesFieldMapper = new Repositories.Mapper();

      @Override
      public User map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        final URL avatarUrl = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[2]);
        final String login = reader.readString($responseFields[3]);
        final String bio = reader.readString($responseFields[4]);
        final String email = reader.readString($responseFields[5]);
        final Followers followers = reader.readObject($responseFields[6], new ResponseReader.ObjectReader<Followers>() {
          @Override
          public Followers read(ResponseReader reader) {
            return followersFieldMapper.map(reader);
          }
        });
        final Following following = reader.readObject($responseFields[7], new ResponseReader.ObjectReader<Following>() {
          @Override
          public Following read(ResponseReader reader) {
            return followingFieldMapper.map(reader);
          }
        });
        final Repositories repositories = reader.readObject($responseFields[8], new ResponseReader.ObjectReader<Repositories>() {
          @Override
          public Repositories read(ResponseReader reader) {
            return repositoriesFieldMapper.map(reader);
          }
        });
        return new User(__typename, name, avatarUrl, login, bio, email, followers, following, repositories);
      }
    }
  }

  public static class Followers {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("totalCount", "totalCount", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int totalCount;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Followers(@Nonnull String __typename, int totalCount) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.totalCount = totalCount;
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

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], totalCount);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Followers{"
          + "__typename=" + __typename + ", "
          + "totalCount=" + totalCount
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Followers) {
        Followers that = (Followers) o;
        return this.__typename.equals(that.__typename)
         && this.totalCount == that.totalCount;
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
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Followers> {
      @Override
      public Followers map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int totalCount = reader.readInt($responseFields[1]);
        return new Followers(__typename, totalCount);
      }
    }
  }

  public static class Following {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("totalCount", "totalCount", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int totalCount;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Following(@Nonnull String __typename, int totalCount) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.totalCount = totalCount;
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

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], totalCount);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Following{"
          + "__typename=" + __typename + ", "
          + "totalCount=" + totalCount
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Following) {
        Following that = (Following) o;
        return this.__typename.equals(that.__typename)
         && this.totalCount == that.totalCount;
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
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Following> {
      @Override
      public Following map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int totalCount = reader.readInt($responseFields[1]);
        return new Following(__typename, totalCount);
      }
    }
  }

  public static class Repositories {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forInt("totalCount", "totalCount", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @Nonnull String __typename;

    final int totalCount;

    private volatile String $toString;

    private volatile int $hashCode;

    private volatile boolean $hashCodeMemoized;

    public Repositories(@Nonnull String __typename, int totalCount) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.totalCount = totalCount;
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

    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeInt($responseFields[1], totalCount);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Repositories{"
          + "__typename=" + __typename + ", "
          + "totalCount=" + totalCount
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
         && this.totalCount == that.totalCount;
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
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Repositories> {
      @Override
      public Repositories map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final int totalCount = reader.readInt($responseFields[1]);
        return new Repositories(__typename, totalCount);
      }
    }
  }
}
