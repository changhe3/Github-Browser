package fragment;

import com.apollographql.apollo.api.GraphqlFragment;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Utils;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Generated;
import javax.annotation.Nonnull;
import type.CustomType;

@Generated("Apollo GraphQL")
public class UserListItemQuery implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("login", "login", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forCustomType("avatarUrl", "avatarUrl", null, false, CustomType.URI, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment UserListItemQuery on User {\n"
      + "  __typename\n"
      + "  login\n"
      + "  avatarUrl\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "User"));

  final @Nonnull String __typename;

  final @Nonnull String login;

  final @Nonnull URL avatarUrl;

  private volatile String $toString;

  private volatile int $hashCode;

  private volatile boolean $hashCodeMemoized;

  public UserListItemQuery(@Nonnull String __typename, @Nonnull String login,
      @Nonnull URL avatarUrl) {
    this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    this.login = Utils.checkNotNull(login, "login == null");
    this.avatarUrl = Utils.checkNotNull(avatarUrl, "avatarUrl == null");
  }

  public @Nonnull String __typename() {
    return this.__typename;
  }

  /**
   * The username used to login.
   */
  public @Nonnull String login() {
    return this.login;
  }

  /**
   * A URL pointing to the user's public avatar.
   */
  public @Nonnull URL avatarUrl() {
    return this.avatarUrl;
  }

  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeString($responseFields[0], __typename);
        writer.writeString($responseFields[1], login);
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[2], avatarUrl);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "UserListItemQuery{"
        + "__typename=" + __typename + ", "
        + "login=" + login + ", "
        + "avatarUrl=" + avatarUrl
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof UserListItemQuery) {
      UserListItemQuery that = (UserListItemQuery) o;
      return this.__typename.equals(that.__typename)
       && this.login.equals(that.login)
       && this.avatarUrl.equals(that.avatarUrl);
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
      h ^= login.hashCode();
      h *= 1000003;
      h ^= avatarUrl.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<UserListItemQuery> {
    @Override
    public UserListItemQuery map(ResponseReader reader) {
      final String __typename = reader.readString($responseFields[0]);
      final String login = reader.readString($responseFields[1]);
      final URL avatarUrl = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[2]);
      return new UserListItemQuery(__typename, login, avatarUrl);
    }
  }
}
