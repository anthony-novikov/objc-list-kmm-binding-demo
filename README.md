# Demo app to reproduce generic type erasure from List<T>

In KMP when `List<T>` is used as a generic type of a class, it's compiled to `NSArray` in Objective C
with erased item type:

```kotlin
class GenericBox<T : Any>(val value: T)

object Demo {
    fun getListOfStringsBox(): GenericBox<List<String>> {
        return GenericBox(listOf("List", "of", "strings"))
    }
}
```

In this example function `getListOfStringsBox()` will be compiled to:

```objectivec
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GenericBox")))
@interface SharedGenericBox<T> : SharedBase
- (instancetype)initWithValue:(T)value __attribute__((swift_name("init(value:)"))) __attribute__((objc_designated_initializer));
@property (readonly) T value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("Demo")))
@interface SharedDemo : SharedBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)demo __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SharedDemo *shared __attribute__((swift_name("shared")));
- (SharedGenericBox<NSArray<NSString *> *> *)getListOfStringsBox __attribute__((swift_name("getListOfStringsBox()")));
@end
```

but then if the function `getListOfStringsBox()` is called and `value` field of `GenericBox` is accessed
the field will contain `NSArray<Any>` instead of `NSArray<NSString>`:

```swift
let demo = Demo.shared
let listOfStrings = demo.getListOfStringsBox().value // NSArray<Any>
```
