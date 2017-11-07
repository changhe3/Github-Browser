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
public class RepoItemQuery implements GraphqlFragment {
  static final ResponseField[] $responseFields = {
    ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forString("nameWithOwner", "nameWithOwner", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forCustomType("url", "url", null, false, CustomType.URI, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forBoolean("viewerHasStarred", "viewerHasStarred", null, false, Collections.<ResponseField.Condition>emptyList()),
    ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList())
  };

  public static final String FRAGMENT_DEFINITION = "fragment RepoItemQuery on Repository {\n"
      + "  __typename\n"
      + "  nameWithOwner\n"
      + "  url\n"
      + "  viewerHasStarred\n"
      + "  id\n"
      + "}";

  public static final List<String> POSSIBLE_TYPES = Collections.unmodifiableList(Arrays.asList( "Repository"));

  final @Nonnull String __typename;

  final @Nonnull String nameWithOwner;

  final @Nonnull URL url;

  final boolean viewerHasStarred;

  final @Nonnull String id;

  private volatile String $toString;

  private volatile int $hashCode;

  private volatile boolean $hashCodeMemoized;

  public RepoItemQuery(@Nonnull String __typename, @Nonnull String nameWithOwner, @Nonnull URL url,
      boolean viewerHasStarred, @Nonnull String id) {
    this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    this.nameWithOwner = Utils.checkNotNull(nameWithOwner, "nameWithOwner == null");
    this.url = Utils.checkNotNull(url, "url == null");
    this.viewerHasStarred = viewerHasStarred;
    this.id = Utils.checkNotNull(id, "id == null");
  }

  public @Nonnull String __typename() {
    return this.__typename;
  }

  /**
   * The repository's name with owner.
   */
  public @Nonnull String nameWithOwner() {
    return this.nameWithOwner;
  }

  /**
   * The HTTP URL for this repository
   */
  public @Nonnull URL url() {
    return this.url;
  }

  /**
   * Returns a boolean indicating whether the viewing user has starred this starrable.
   */
  public boolean viewerHasStarred() {
    return this.viewerHasStarred;
  }

  public @Nonnull String id() {
    return this.id;
  }

  public ResponseFieldMarshaller marshaller() {
    return new ResponseFieldMarshaller() {
      @Override
      public void marshal(ResponseWriter writer) {
        writer.writeString($responseFields[0], __typename);
        writer.writeString($responseFields[1], nameWithOwner);
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[2], url);
        writer.writeBoolean($responseFields[3], viewerHasStarred);
        writer.writeCustom((ResponseField.CustomTypeField) $responseFields[4], id);
      }
    };
  }

  @Override
  public String toString() {
    if ($toString == null) {
      $toString = "RepoItemQuery{"
        + "__typename=" + __typename + ", "
        + "nameWithOwner=" + nameWithOwner + ", "
        + "url=" + url + ", "
        + "viewerHasStarred=" + viewerHasStarred + ", "
        + "id=" + id
        + "}";
    }
    return $toString;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof RepoItemQuery) {
      RepoItemQuery that = (RepoItemQuery) o;
      return this.__typename.equals(that.__typename)
       && this.nameWithOwner.equals(that.nameWithOwner)
       && this.url.equals(that.url)
       && this.viewerHasStarred == that.viewerHasStarred
       && this.id.equals(that.id);
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
      h ^= nameWithOwner.hashCode();
      h *= 1000003;
      h ^= url.hashCode();
      h *= 1000003;
      h ^= Boolean.valueOf(viewerHasStarred).hashCode();
      h *= 1000003;
      h ^= id.hashCode();
      $hashCode = h;
      $hashCodeMemoized = true;
    }
    return $hashCode;
  }

  public static final class Mapper implements ResponseFieldMapper<RepoItemQuery> {
    @Override
    public RepoItemQuery map(ResponseReader reader) {
      final String __typename = reader.readString($responseFields[0]);
      final String nameWithOwner = reader.readString($responseFields[1]);
      final URL url = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[2]);
      final boolean viewerHasStarred = reader.readBoolean($responseFields[3]);
      final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[4]);
      return new RepoItemQuery(__typename, nameWithOwner, url, viewerHasStarred, id);
    }
  }
}
