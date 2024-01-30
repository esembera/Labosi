import gilp
from gilp.visualize import simplex_visual
import numpy as np
from gilp.simplex import LP

A = np.array([[1, 1, 1],
              [10, 5, 3]])
b = np.array([[10],
              [70]])
c = np.array([[20],
              [16],
              [10]])
lp = LP(A,b,c)

simplex_visual(lp).show()