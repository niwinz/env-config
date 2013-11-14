# datasource

Clojure library that helps store environment dependent configuration
in a separate file.

It works well for store database connection configuration
for distinct environments on your web project but also can helps store
any configuration data that should be depending on current environment.

## Install

Put this on your leiningen depdencies:

```clojure
[datasource "0.1.0"]
```

## Usage

### Config file location

Put `datasource.clj` file on any directory that are included on your class path. As simple approximation,
you can put it on `resources/` directory.

Also, you can create new directory like `config/` and add it to `:resource-paths` on your leiningen
project.clj file, and obviously, put `datasource.clj` there.

### Config file format

Config file format is a clojure file, but with some limitations, it should only contain a clojure
hash-map as first declaration and should contain a environment name as keyword on first level keys.

This is a simple exaple:

```clojure
{:dev {:database "jdbc:postgresql://127.0.0.1:5432/foo?foo"}
 :pro {:database "jdbc:postgresql://10.0.0.1:5432/foo?foo"}}
```

### Usage guide

datasource mainly consist on one function: `get-config` that reads environment variables and system properties.

This is a simple example for use it:

```clojure
(require '[datasource.core :as ds])
(ds/get-config)
;; -> {:database "jdbc:postgresql://127.0.0.1:5432/foo?foo"}
```

And if you previously set a diferent environ name:

```
export ENVIRON=pro
```

A result of executing a previous code should out a configuration
associated with `:pro` environment

```clojure
(ds/get-config)
;; -> {:database "jdbc:postgresql://10.0.0.1:5432/foo?foo"}
```

## License

Copyright © 2013 Andrey Antukh <niwi@niwi.be> Distributed under the Apache 2.0 License.

This code includes some code from https://github.com/weavejester/environ that are licensed
by James Reeves with Eclipse Public License.
