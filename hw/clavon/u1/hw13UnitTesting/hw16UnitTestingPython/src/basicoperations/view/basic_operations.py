import sys
import os

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '../../')))

from basicoperations.model.operation import Operation

def main():
    print("Operations")
    
    operation = Operation()
    
    print(f"The addition of 1.2 and 2.4 ---> {operation.add(1.2, 2.4)}")
    print(f"The addition of 1.2 and 2.4 ---> {operation.add(1.2, 2.4)}")
    print(f"The addition of 1.2 and 2.4 ---> {operation.add(1.2, 2.4)}")
    
    try:
        print(f"divide ---> {operation.divide(1, 0)}")
    except ValueError as e:
        print(f"divide ---> Error: {e}")

if __name__ == "__main__":
    main()