package type;

import javax.annotation.Generated;

/**
 * Represents the individual results of a search.
 */
@Generated("Apollo GraphQL")
public enum SearchType {
  /**
   * Returns results matching issues in repositories.
   */
  ISSUE,

  /**
   * Returns results matching repositories.
   */
  REPOSITORY,

  /**
   * Returns results matching users on GitHub.
   */
  USER
}
