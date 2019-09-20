package io.pileworx.akka.http.rest.hal

object Relations {
  /**
    * @see { @link https://tools.ietf.org/html/rfc6903}
    */
  val ABOUT: String = "about"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-alternate}
    */
  val ALTERNATE: String = "alternate"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val APPENDIX: String = "appendix"

  /**
    * @see { @link https://www.w3.org/TR/2011/WD-html5-20110113/links.html#rel-archives}
    */
  val ARCHIVES: String = "archives"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-author}
    */
  val AUTHOR: String = "author"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7725}
    */
  val BLOCKED_BY: String = "blocked-by"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-bookmark}
    */
  val BOOKMARK: String = "bookmark"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6596}
    */
  val CANONICAL: String = "canonical"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val CHAPTER: String = "chapter"

  /**
    * @see { @link https://datatracker.ietf.org/doc/draft-vandesompel-citeas/}
    */
  val CITE_AS: String = "cite-as"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6573}
    */
  val COLLECTION: String = "collection"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val CONTENTS: String = "contents"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7991}
    */
  val CONVERTED_FROM: String = "convertedFrom"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val COPYRIGHT: String = "copyright"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6861}
    */
  val CREATE_FORM: String = "create-form"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val CURRENT: String = "current"

  /**
    * @see { @link https://www.w3.org/TR/powder-dr/#assoc-linking}
    */
  val DESCRIBED_BY: String = "describedBy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6892}
    */
  val DESCRIBES: String = "describes"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6579}
    */
  val DISCLOSURE: String = "disclosure"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val DNS_PREFETCH: String = "dns-prefetch"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6249}
    */
  val DUPLICATE: String = "duplicate"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val EDIT: String = "edit"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6861}
    */
  val EDIT_FORM: String = "edit-form"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val EDIT_MEDIA: String = "edit-media"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val ENCLOSURE: String = "enclosure"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val FIRST: String = "first"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val GLOSSARY: String = "glossary"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-help}
    */
  val HELP: String = "help"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6690}
    */
  val HOSTS: String = "hosts"

  /**
    * @see { @link https://pubsubhubbub.googlecode.com}
    */
  val HUB: String = "hub"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-icon}
    */
  val ICON: String = "icon"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val INDEX: String = "index"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalAfter section 4.2.21}
    */
  val INTERVAL_AFTER: String = "intervalAfter"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalBefore section 4.2.22}
    */
  val INTERVAL_BEFORE: String = "intervalBefore"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalContains section 4.2.23}
    */
  val INTERVAL_CONTAINS: String = "intervalContains"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalDisjoint section 4.2.24}
    */
  val INTERVAL_DISJOINT: String = "intervalDisjoint"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalDuring section 4.2.25}
    */
  val INTERVAL_DURING: String = "intervalDuring"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalEquals section 4.2.26}
    */
  val INTERVAL_EQUALS: String = "intervalEquals"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalFinishedBy section 4.2.27}
    */
  val INTERVAL_FINISHED_BY: String = "intervalFinishedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalFinishes section 4.2.28}
    */
  val INTERVAL_FINISHES: String = "intervalFinishes"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalIn section 4.2.29}
    */
  val INTERVAL_IN: String = "intervalIn"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalMeets section 4.2.30}
    */
  val INTERVAL_MEETS: String = "intervalMeets"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalMetBy section 4.2.31}
    */
  val INTERVAL_MET_BY: String = "intervalMetBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalOverlappedBy section 4.2.32}
    */
  val INTERVAL_OVERLAPPED_BY: String = "intervalOverlappedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalOverlaps section 4.2.33}
    */
  val INTERVAL_OVERLAPS: String = "intervalOverlaps"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalStartedBy section 4.2.34}
    */
  val INTERVAL_STARTED_BY: String = "intervalStartedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalStarts section 4.2.35}
    */
  val INTERVAL_STARTS: String = "intervalStarts"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6573}
    */
  val ITEM: String = "item"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val LAST: String = "last"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val LATEST_VERSION: String = "latest-version"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4946}
    */
  val LICENSE: String = "license"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6415}
    */
  val LRDD: String = "lrdd"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val MEMENTO: String = "memento"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5989}
    */
  val MONITOR: String = "monitor"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5989}
    */
  val MONITOR_GROUP: String = "monitor-group"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-next}
    */
  val NEXT: String = "next"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val NEXT_ARCHIVE: String = "next-archive"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-nofollow}
    */
  val NOFOLLOW: String = "nofollow"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-noreferrer}
    */
  val NOREFERRER: String = "noreferrer"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val ORIGINAL: String = "original"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val PAYMENT: String = "payment"

  /**
    * @see { @link https://www.hixie.ch/specs/pingback/pingback}
    */
  val PINGBACK: String = "pingback"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val PRECONNECT: String = "preconnect"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val PREDECESSOR_VERSION: String = "predecessor-version"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val PREFETCH: String = "prefetch"

  /**
    * @see { @link https://www.w3.org/TR/preload/}
    */
  val PRELOAD: String = "preload"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val PRERENDER: String = "prerender"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-prev}
    */
  val PREV: String = "prev"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 3}
    */
  val PREVIEW: String = "preview"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val PREVIOUS: String = "previous"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val PREV_ARCHIVE: String = "prev-archive"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 4}
    */
  val PRIVACY_POLICY: String = "privacy-policy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6906}
    */
  val PROFILE: String = "profile"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val RELATED: String = "related"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8040}
    */
  val RESTCONF: String = "restconf"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4685}
    */
  val REPLIES: String = "replies"

  /**
    * @see { @link http://www.opensearch.org/Specifications/OpenSearch/1.1}
    */
  val SEARCH: String = "search"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val SECTION: String = "section"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val SELF: String = "self"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val SERVICE: String = "service"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val START: String = "start"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-stylesheet}
    */
  val STYLESHEET: String = "stylesheet"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val SUBSECTION: String = "subsection"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val SUCCESSOR_VERSION: String = "successor-versions"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-tag}
    */
  val TAG: String = "tag"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 5}
    */
  val TERMS_OF_SERVICE: String = "terms-of-service"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val TIMEGATE: String = "timegate"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val TIMEMAP: String = "timemap"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 6}
    */
  val TYPE: String = "type"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val UP: String = "up"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val VERSION_HISTORY: String = "version-history"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val VIA: String = "via"

  /**
    * @see { @link https://www.w3.org/TR/webmention/}
    */
  val WEBMENTION: String = "webmention"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val WORKING_COPY: String = "working-copy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val WORKING_COPY_OF: String = "working-copy-of"
}
