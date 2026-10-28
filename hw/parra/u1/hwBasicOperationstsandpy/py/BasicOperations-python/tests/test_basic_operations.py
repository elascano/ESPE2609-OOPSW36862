import contextlib
import io
import unittest

from ec.edu.espe.basicoperations.view import basic_operations


class BasicOperationsTest(unittest.TestCase):
    def test_main(self):
        buf = io.StringIO()
        with contextlib.redirect_stdout(buf):
            basic_operations.main()
        lines = buf.getvalue().splitlines()
        self.assertEqual(lines[0], "Operations")
        prefix = "The addition of 1.2 and 2.4 ---> "
        for line in lines[1:4]:
            self.assertTrue(line.startswith(prefix))
            self.assertAlmostEqual(float(line[len(prefix):]), 3.6)
        self.assertEqual(lines[-1], "divide ---> inf")


if __name__ == "__main__":
    unittest.main()
