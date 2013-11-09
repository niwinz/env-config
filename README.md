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

As first step you should put `datasource.clj` file on your class path. For it, we have
some options:

- Put it on `resource/` directory.
- Add `config/` directory on `:resource-paths` on your leiningen config and put a config file on it.

All of them options are valid. datasource library always search `datasource.clj` on application
classpath.

### Config file format

The format is simple. It can not have any imports or namespace defintion at start. It shoould only
contain a clojure hash-map with environment name keywords as first level keys. Example:

```clojure
{:dev {:database "jdbc:postgresql://127.0.0.1:5432/foo?foo"}
 :pro {:database "jdbc:postgresql://10.0.0.1:5432/foo?foo"}}
```

### Api

For use it on your code, you should import it:

```clojure
(require '[datasource.core :as ds])
```

And executing simple function you can obtain a environment configuration
for current environment (if no environment was set, `:dev` environment name is set as default).

```clojure
(ds/get-config)
;; -> {:database "jdbc:postgresql://127.0.0.1:5432/foo?foo"}
```

## License

Copyright © 2013 Andrey Antukh <niwi@niwi.be>

Distributed under the Apache 2.0 License.
