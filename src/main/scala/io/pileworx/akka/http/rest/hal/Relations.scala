package io.pileworx.akka.http.rest.hal

object Relations {
  /**
    * @see { @link https://tools.ietf.org/html/rfc6903}
    */
  val About: String = "about"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-alternate}
    */
  val Alternate: String = "alternate"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Appendix: String = "appendix"

  /**
    * @see { @link https://www.w3.org/TR/2011/WD-html5-20110113/links.html#rel-archives}
    */
  val Archives: String = "archives"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-author}
    */
  val Author: String = "author"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7725}
    */
  val BlockedBy: String = "blocked-by"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-bookmark}
    */
  val Bookmark: String = "bookmark"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6596}
    */
  val Canonical: String = "canonical"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Chapter: String = "chapter"

  /**
    * @see { @link https://datatracker.ietf.org/doc/draft-vandesompel-citeas/}
    */
  val CiteAs: String = "cite-as"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6573}
    */
  val Collection: String = "collection"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val CONTENTS: String = "contents"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7991}
    */
  val ConvertedFrom: String = "convertedFrom"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Copyright: String = "copyright"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6861}
    */
  val CreateForm: String = "create-form"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val Current: String = "current"

  /**
    * @see { @link https://www.w3.org/TR/powder-dr/#assoc-linking}
    */
  val DescribedBy: String = "describedBy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6892}
    */
  val Describes: String = "describes"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6579}
    */
  val Disclosure: String = "disclosure"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val DnsPrefetch: String = "dns-prefetch"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6249}
    */
  val Duplicate: String = "duplicate"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val Edit: String = "edit"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6861}
    */
  val EditForm: String = "edit-form"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val EditMedia: String = "edit-media"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val Enclosure: String = "enclosure"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val First: String = "first"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Glossary: String = "glossary"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-help}
    */
  val Help: String = "help"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6690}
    */
  val Hosts: String = "hosts"

  /**
    * @see { @link https://pubsubhubbub.googlecode.com}
    */
  val Hub: String = "hub"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-icon}
    */
  val Icon: String = "icon"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Index: String = "index"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalAfter section 4.2.21}
    */
  val IntervalAfter: String = "intervalAfter"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalBefore section 4.2.22}
    */
  val IntervalBefore: String = "intervalBefore"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalContains section 4.2.23}
    */
  val IntervalContains: String = "intervalContains"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalDisjoint section 4.2.24}
    */
  val IntervalDisjoint: String = "intervalDisjoint"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalDuring section 4.2.25}
    */
  val IntervalDuring: String = "intervalDuring"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalEquals section 4.2.26}
    */
  val IntervalEquals: String = "intervalEquals"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalFinishedBy section 4.2.27}
    */
  val IntervalFinishedBy: String = "intervalFinishedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalFinishes section 4.2.28}
    */
  val IntervalFinishes: String = "intervalFinishes"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalIn section 4.2.29}
    */
  val IntervalIn: String = "intervalIn"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalMeets section 4.2.30}
    */
  val IntervalMeets: String = "intervalMeets"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalMetBy section 4.2.31}
    */
  val IntervalMetBy: String = "intervalMetBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalOverlappedBy section 4.2.32}
    */
  val IntervalOverlappedBy: String = "intervalOverlappedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalOverlaps section 4.2.33}
    */
  val IntervalOverlaps: String = "intervalOverlaps"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalStartedBy section 4.2.34}
    */
  val IntervalStartedBy: String = "intervalStartedBy"

  /**
    * @see { @link https://www.w3.org/TR/owl-time/#time:intervalStarts section 4.2.35}
    */
  val IntervalStarts: String = "intervalStarts"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6573}
    */
  val Item: String = "item"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val Last: String = "last"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val LatestVersion: String = "latest-version"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4946}
    */
  val License: String = "license"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6415}
    */
  val Lrdd: String = "lrdd"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val Memento: String = "memento"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5989}
    */
  val Monitor: String = "monitor"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5989}
    */
  val MonitorGroup: String = "monitor-group"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-next}
    */
  val Next: String = "next"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val NextArchive: String = "next-archive"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-nofollow}
    */
  val NOFOLLOW: String = "nofollow"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-noreferrer}
    */
  val NoReferrer: String = "noreferrer"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val Original: String = "original"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val Payment: String = "payment"

  /**
    * @see { @link https://www.hixie.ch/specs/pingback/pingback}
    */
  val PingBack: String = "pingback"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val PreConnect: String = "preconnect"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val PredecessorVersion: String = "predecessor-version"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val Prefetch: String = "prefetch"

  /**
    * @see { @link https://www.w3.org/TR/preload/}
    */
  val Preload: String = "preload"

  /**
    * @see { @link https://www.w3.org/TR/resource-hints/}
    */
  val PreRender: String = "prerender"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-prev}
    */
  val Prev: String = "prev"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 3}
    */
  val Preview: String = "preview"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Previous: String = "previous"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5005}
    */
  val PrevArchive: String = "prev-archive"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 4}
    */
  val PrivacyPolicy: String = "privacy-policy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6906}
    */
  val Profile: String = "profile"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val Related: String = "related"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8040}
    */
  val RestConf: String = "restconf"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4685}
    */
  val Replies: String = "replies"

  /**
    * @see { @link http://www.opensearch.org/Specifications/OpenSearch/1.1}
    */
  val Search: String = "search"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Section: String = "section"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val Self: String = "self"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5023}
    */
  val Service: String = "service"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Start: String = "start"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-stylesheet}
    */
  val Stylesheet: String = "stylesheet"

  /**
    * @see { @link https://www.w3.org/TR/1999/REC-html401-19991224}
    */
  val Subsection: String = "subsection"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val SuccessorVersions: String = "successor-versions"

  /**
    * @see { @link https://www.w3.org/TR/html5/links.html#link-type-tag}
    */
  val tag: String = "tag"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 5}
    */
  val TermsOfService: String = "terms-of-service"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val TimeGate: String = "timegate"

  /**
    * @see { @link https://tools.ietf.org/html/rfc7089}
    */
  val TimeMap: String = "timemap"

  /**
    * @see { @link https://tools.ietf.org/html/rfc6903, section 6}
    */
  val Type: String = "type"

  /**
    * @see { @link https://tools.ietf.org/html/rfc8288}
    */
  val Up: String = "up"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val VersionHistory: String = "version-history"

  /**
    * @see { @link https://tools.ietf.org/html/rfc4287}
    */
  val Via: String = "via"

  /**
    * @see { @link https://www.w3.org/TR/webmention/}
    */
  val WebMention: String = "webmention"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val WorkingCopy: String = "working-copy"

  /**
    * @see { @link https://tools.ietf.org/html/rfc5829}
    */
  val WorkingCopyOf: String = "working-copy-of"
}