# Conway


**Synopsis:**

>Conway's Game Of Life is a simulator in which a grid of cells exist. The grid evolves towards the future given 4 rules:

>1. Any living cell with less than 2 live neighbors dies, as if from underpopulation.
>2. Any living cell with exactly 2 or 3 live neighbors continues on to the next generation.
>3. Any living cell with more than 4 live neighbors dies, as if from overpopulation.
>4. Any dead cell with exactly 3 live neighbors becomes alive, as if through reproduction.


**Implementation Details**
> Coded entirely in Java, using javax.Swing library. Can be run from desktop


**Further Improvements**
>1. Currently uses paint() to display grid and repaint() to update grid through generations. This causes the screen to flicker 
> for grids larger than approximately 15x15 cells.

>2. Because the grid is a 2D array, any "gliders" etc. do not continue off of the grid, and instead the evolutions are bound by 
>   the dimensions of the array.
