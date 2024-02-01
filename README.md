[![Build Status](https://dev.azure.com/knotx/Knotx/_apis/build/status/Knotx.knotx-commons?branchName=master)](https://dev.azure.com/knotx/Knotx/_build/latest?definitionId=5&branchName=master)
[![CodeFactor](https://www.codefactor.io/repository/github/knotx/knotx-commons/badge)](https://www.codefactor.io/repository/github/knotx/knotx-commons)
[![codecov](https://codecov.io/gh/Knotx/knotx-commons/branch/master/graph/badge.svg)](https://codecov.io/gh/Knotx/knotx-commons)
[![Gradle Status](https://gradleupdate.appspot.com/Knotx/knotx-commons/status.svg)](https://gradleupdate.appspot.com/Knotx/knotx-commons/status)

# Knot.x Commons
Knot.x Commons contains support classes (utils, helpers, etc.). This module can be used via Knot.x API modules so
all dependencies to Knot.x modules are not allowed.

## Cache

`Cache` and `CacheFactory` interfaces can provide Knot.x with various cache implementations (e.g. in-memory, Redis, Elasticache etc.).

These implementations - when configured correctly for Service Provider Inteface - can then be used by `CacheActionFactory` in [Knot.x Fragments](https://github.com/Knotx/knotx-fragments). 
The desired implementation is selected using `getType()` method from `CacheFactory` interface. For reference see `CacheFactory`'s Javadoc.

Currently, Knot.x provides `in-memory` Cache implementation, based on Google Guava cache.
