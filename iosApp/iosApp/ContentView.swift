import SwiftUI
import shared

struct ContentView: View {
    let demo = Demo.shared

	var body: some View {
        let listOfStrings1 = demo.getListOfStringsBox1().value // NSArray<Any>
        let listOfStrings2 = demo.getListOfStringsBox2().value // NSArray<NSString>
        let box = demo.getBoxOfStringBoxes().value // GenericBox<ChildGenericBox<String>>
        let string = demo.getStringBox().value // NSString

        VStack {
            // Text(listOfStrings1.joined()) // <-- Doesn't work becasue listOfStrings1 is not Array<NSString>
            Text(listOfStrings2.joined())
            Text(String(box.value))
            Text(String(string))
        }
        
	}
}

extension Array<NSString> {
    func joined(_ separator: String = " ") -> String {
        map { String($0) }.joined(separator: separator)
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
