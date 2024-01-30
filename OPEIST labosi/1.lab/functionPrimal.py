import pulp

# Create the linear programming problem
problem = pulp.LpProblem("Optimalni_transport", pulp.LpMaximize)

# Define variables
x = pulp.LpVariable('x', lowBound=0, cat='Integer')  # Trucks with a capacity of 10 tons
y = pulp.LpVariable('y', lowBound=0, cat='Integer')  # Trucks with a capacity of 5 tons
z = pulp.LpVariable('z', lowBound=0, cat='Integer')  # Trucks with a capacity of 3 tons

# Add the objective function
problem += 20 * x + 16 * y + 10 * z, "Potrosnja_goriva"

# Add constraints
problem += x + y + z == 10  # Total number of selected trucks
problem += 10 * x + 5 * y + 3 * z == 70  # Total truck capacity

# Solve the problem
problem.solve()

# Print the solution
print("Objective Function Value:", problem.objective.value())
print("Solution:")
print(f"Number of trucks with a capacity of 10 tons: {x.value()}")
print(f"Number of trucks with a capacity of 5 tons: {y.value()}")
print(f"Number of trucks with a capacity of 3 tons: {z.value()}")

