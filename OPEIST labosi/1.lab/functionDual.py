import pulp

# Stvaranje dualnog problema linearnog programiranja
dual_problem = pulp.LpProblem("Dualni_problem", pulp.LpMinimize)

# Definiranje dualnih varijabli
u = pulp.LpVariable('u', lowBound=0)
v = pulp.LpVariable('v', lowBound=0)

# Dodavanje ciljne funkcije (maksimizacija)
dual_problem += 10 * u + 70 * v, "Dualna_funkcija"

# Dodavanje ograničenja
dual_problem += u + 10 * v >= 20
dual_problem += u + 5 * v >= 16
dual_problem += u + 3 * v >= 10

# Rješavanje dualnog problema
dual_problem.solve()

# Ispis rješenja
print("Vrijednost dualne funkcije cilja:", pulp.value(dual_problem.objective))
print("Rezultat:")
print(f"Dualna varijabla u: {pulp.value(u)}")
print(f"Dualna varijabla v: {pulp.value(v)}")
