from rectangle_perimeter import RectanglePerimeter

if __name__ == "__main__":
    base = float(input("Base: "))
    height = float(input("Height: "))
    result = RectanglePerimeter().calculatePerimeter(base, height)
    print(f"Rectangle perimeter: {result:.2f}")
