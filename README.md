# Tagless Final pattern styles in scala

# Research

Tagless Final algebras and Streaming
- https://typelevel.org/blog/2018/05/09/tagless-final-streaming.html

> **Final thoughts**
> I think we got quite far with all these abstractions, giving us the chance to write clean and elegant code in a pure functional programming style, and there’s even more! Other topics worth mentioning that might require a blog post on their own are:

> **Dependency Injection**
> Tagless Final + implicits (MTL style) enables DI in an elegant way.

> **Algebras Composition**
> It is very common to have multiple algebras with a different F[_] implementation. In some cases, FunctionK (a.k.a. natural transformation) can be the solution.

Luka Jacobowitz, Building a Tagless Final DSL for WebGL in Scala
- https://www.youtube.com/watch?v=1h11efA4k8E

Optimizing Tagless Final – Saying farewell to Free
- https://typelevel.org/blog/2017/12/27/optimizing-final-tagless.html

Difference between mtl-style and tagless final style
- https://gist.github.com/kaishh/00c5ef96c1c1679a1edf7056ea55b49d

Free Monad vs Tagless Final
- https://medium.com/@agaro1121/free-monad-vs-tagless-final-623f92313eac
