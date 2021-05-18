# OAuth2 for Java

Zero dependencies OAuth2 client for Java.

The repository goal is to provide simple and zero dependencies OAuth2 client.

The inspiration for the current implementation comes from [OAuth2 for Go library](https://github.com/golang/oauth2), 
implemented without third-party dependencies, contains enough functionality to do authentication, and super easy to use.

## Why?

So why the world needs yet another Java library to do [OAuth2](https://oauth.net/2/) authentication? 
The world does not. 
The library was born from two factors: it's already been a while I was coding in Java, 
and I am starting to be a bit obsessed with the idea of zero dependency libraries in Java.

Here is the thing, the Java projects world is always coming up with a big framework in the backbone. 
It might be [WildFly](https://www.wildfly.org), or [Micronaut](https://micronaut.io), or [Quarkus](https://quarkus.io), or [Vertx](https://vertx.io), or [Spring](https://spring.io). 
The frameworks care, they are here for you, they are holding your back, they give you everything you need. 
But what if they don't? What if they do not have a tool you need? Just a little one? When what?

When you have either wait until your framework gives you a tool (the feature request is nothing more than active waiting). 
Or you are writing your tool yourself (maybe with PR to the framework, why not?). 
Or you are changing the framework (good luck with switching the platform, this is a long, exhausting journey, 
I've been there, I'm talking from experience).

Each framework comes up with a dozen dependencies. 
All right, yeah, the frameworks are modular, and you include to your project only modules you need. 
I've got it. Understood. 
What about transitive dependencies? Exactly. 
Moreover, each additional library has its dependencies, and library dependencies have transitive dependencies. 
At the end, you have five ways to make HTTP requests, three ways to parse JSON responses, ten ways to split the strings. 
And they are conflicting with each other. Yep. 
One library abandon years ago, not it was not you who includes it. 
No, it won't work without it. Well, you've got the drill.

## What if?

What if the library does the job? What if it follows UNIX principal do one thing but do it right? 
Without dependencies, without conflicts, and the necessity to update the code every two-five years? 
Is it possible? I think it is possible. I think it's the natural way how to do things.

That's why.

## Build.

The project is built with [Maven](http://maven.apache.org) and requires [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or later.

That's it. Right. Just like that.
