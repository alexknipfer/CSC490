	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 12
	.globl	_main
	.align	4, 0x90
_main:                                  ## @main
	.cfi_startproc
## BB#0:
	pushq	%rbp
Ltmp0:
	.cfi_def_cfa_offset 16
Ltmp1:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Ltmp2:
	.cfi_def_cfa_register %rbp
	movl	$0, -4(%rbp)
	movl	$12, -8(%rbp)
	movl	$32, -12(%rbp)
	cmpl	$3, -8(%rbp)
	jle	LBB0_3
## BB#1:
	cmpl	$50, -8(%rbp)
	jge	LBB0_3
## BB#2:
	jmp	LBB0_3
LBB0_3:
	movl	-4(%rbp), %eax
	popq	%rbp
	retq
	.cfi_endproc


.subsections_via_symbols
