# FileParser

Any NOTE comments in code reflect assumptions made

The first argument represents the file name as requested.

The only unique argument for fixed-width files is a second one where a number must be specified for handling a constant fixed-width column size.  Thus, the assumption is that all columns are the specified n characters wide.  (Ideally, the input should eventually encompass a csv string for handling varying column widths.)
